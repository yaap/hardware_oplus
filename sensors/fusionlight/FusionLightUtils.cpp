/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#include "FusionLightUtils.h"

#include <fstream>

#include <aidl/vendor/oplus/hardware/displaypanelfeature/IDisplayPanelFeature.h>
#include <aidl/vendor/oplus/hardware/oplusSensor/ISensorFeature.h>
#include <android-base/logging.h>
#include <android-base/parsedouble.h>
#include <android-base/parseint.h>
#include <android-base/stringprintf.h>
#include <android-base/strings.h>
#include <json/json.h>

namespace android {
namespace hardware {
namespace sensors {
namespace V2_1 {
namespace subhal {
namespace implementation {
namespace fusionlight {
namespace {

using aidl::vendor::oplus::hardware::displaypanelfeature::IDisplayPanelFeature;
using aidl::vendor::oplus::hardware::oplusSensor::ISensorFeature;
using namespace std::chrono_literals;

constexpr int32_t kHighPwmSensorType = 33171070;
constexpr int32_t kPanelInfoFeature = 9;
constexpr int32_t kMainPanelStageFeature = 0x22;
constexpr int32_t kDefaultPanelStage = 4;
constexpr auto kCwbWeightsPath = "/odm/etc/display/cwb_weightspos.json";
constexpr auto kDefaultScreenshotPeriod = 250ms;

bool ReadNumber(const Json::Value& value, double& result) {
    if (value.isString()) {
        if (!android::base::ParseDouble(android::base::Trim(value.asString()), &result)) {
            return false;
        }
    } else if (value.isNumeric()) {
        result = value.asDouble();
    } else {
        return false;
    }
    return std::isfinite(result);
}

bool ReadInt(const Json::Value& value, int32_t& result) {
    if (value.isString()) {
        return android::base::ParseInt(android::base::Trim(value.asString()), &result);
    }
    if (!value.isInt()) {
        return false;
    }
    result = value.asInt();
    return true;
}

bool ReadRequiredInt(const Json::Value& object, const char* name, int32_t& result) {
    return object.isObject() && object.isMember(name) && ReadInt(object[name], result);
}

template <typename Duration>
bool ReadRequiredDuration(const Json::Value& object, const char* name, Duration& result) {
    int32_t value;
    if (!ReadRequiredInt(object, name, value)) {
        return false;
    }
    result = Duration(value);
    return true;
}

bool ParseRanges(const Json::Value& array, const char* min_name, const char* max_name,
                 std::vector<ValueRange>& ranges) {
    if (!array.isArray() || array.empty()) {
        return false;
    }

    int32_t max_level = -1;
    for (const auto& item : array) {
        int32_t level;
        if (!ReadRequiredInt(item, "Level", level) || level < 0) {
            return false;
        }
        max_level = std::max(max_level, level);
    }

    ranges.assign(max_level + 1, {});
    std::vector<bool> seen(ranges.size());
    for (const auto& item : array) {
        int32_t level;
        double min;
        double max;
        if (!ReadRequiredInt(item, "Level", level) || !ReadNumber(item[min_name], min) ||
            !ReadNumber(item[max_name], max) || min > max || seen[level]) {
            return false;
        }
        ranges[level] = {min, max};
        seen[level] = true;
    }
    return std::all_of(seen.begin(), seen.end(), [](bool value) { return value; });
}

bool ParseLuxCoefficients(const Json::Value& array, std::vector<Channels>& coefficients) {
    if (!array.isArray() || array.empty()) {
        return false;
    }

    int32_t max_level = -1;
    for (const auto& item : array) {
        int32_t level;
        if (!ReadRequiredInt(item, "Level", level) || level < 0) {
            return false;
        }
        max_level = std::max(max_level, level);
    }

    constexpr const char* kNames[] = {"ChannelR", "ChannelG", "ChannelB", "ChannelC"};
    coefficients.resize(max_level + 1);
    std::vector<bool> seen(coefficients.size());
    for (const auto& item : array) {
        int32_t level;
        if (!ReadRequiredInt(item, "Level", level) || seen[level]) {
            return false;
        }
        for (int channel = 0; channel < kChannelCount; ++channel) {
            if (!ReadNumber(item[kNames[channel]], coefficients[level][channel])) {
                return false;
            }
        }
        seen[level] = true;
    }
    return std::all_of(seen.begin(), seen.end(), [](bool value) { return value; });
}

template <typename Model>
bool ParseChannelModels(const Json::Value& array,
                        std::vector<std::array<Model, kChannelCount>>& models) {
    if (!array.isArray() || array.size() != kChannelCount) {
        return false;
    }

    int32_t max_level = -1;
    for (const auto& channel_entry : array) {
        if (!channel_entry["Parameter"].isArray()) {
            return false;
        }
        for (const auto& item : channel_entry["Parameter"]) {
            int32_t level;
            if (!ReadRequiredInt(item, "level", level) || level < 0) {
                return false;
            }
            max_level = std::max(max_level, level);
        }
    }
    if (max_level < 0) {
        return false;
    }

    models.resize(max_level + 1);
    std::vector<std::array<bool, kChannelCount>> seen(models.size());
    for (const auto& channel_entry : array) {
        int32_t channel;
        if (!ReadRequiredInt(channel_entry, "channel", channel) || channel < 0 ||
            channel >= kChannelCount) {
            return false;
        }
        for (const auto& item : channel_entry["Parameter"]) {
            int32_t level;
            if (!ReadRequiredInt(item, "level", level) || seen[level][channel]) {
                return false;
            }
            for (int parameter = 0; parameter < Model::SizeAtCompileTime; ++parameter) {
                const std::string name = "Parameter" + std::to_string(parameter);
                if (!ReadNumber(item[name], models[level][channel][parameter])) {
                    return false;
                }
            }
            seen[level][channel] = true;
        }
    }

    for (const auto& level : seen) {
        if (!std::all_of(level.begin(), level.end(), [](bool value) { return value; })) {
            return false;
        }
    }
    return true;
}

bool ParseGolden(const Json::Value& array, std::vector<Channels>& golden) {
    if (!array.isArray() || array.empty()) {
        return false;
    }
    int32_t max_level = -1;
    for (const auto& item : array) {
        int32_t level;
        if (!ReadRequiredInt(item, "level", level) || level < 0) {
            return false;
        }
        max_level = std::max(max_level, level);
    }

    constexpr const char* kNames[] = {"RGolden", "GGolden", "BGolden", "CGolden"};
    golden.resize(max_level + 1);
    std::vector<bool> seen(golden.size());
    for (const auto& item : array) {
        int32_t level;
        if (!ReadRequiredInt(item, "level", level) || seen[level]) {
            return false;
        }
        for (int channel = 0; channel < kChannelCount; ++channel) {
            if (!ReadNumber(item[kNames[channel]], golden[level][channel])) {
                return false;
            }
        }
        seen[level] = true;
    }
    return std::all_of(seen.begin(), seen.end(), [](bool value) { return value; });
}

bool ParseChannelThresholds(const Json::Value& array, std::vector<ChannelLimits>& thresholds) {
    if (!array.isArray()) {
        return false;
    }
    constexpr const char* kNames[] = {"RMax", "GMax", "BMax", "CMax"};
    thresholds.clear();
    thresholds.reserve(array.size());
    for (const auto& item : array) {
        ChannelLimits threshold;
        for (int channel = 0; channel < kChannelCount; ++channel) {
            if (!ReadInt(item[kNames[channel]], threshold[channel])) {
                return false;
            }
        }
        thresholds.push_back(threshold);
    }
    return true;
}

bool ParseCZeroThresholds(const Json::Value& array, std::vector<double>& thresholds) {
    if (!array.isArray() || array.empty()) {
        return false;
    }
    thresholds.assign(array.size(), 0.0);
    std::vector<bool> seen(array.size());
    for (const auto& item : array) {
        int32_t level;
        double min;
        if (!ReadRequiredInt(item, "Level", level) || level < 0 || level >= array.size() ||
            !ReadNumber(item["CZeroMin"], min) || seen[level]) {
            return false;
        }
        thresholds[level] = min;
        seen[level] = true;
    }
    return std::all_of(seen.begin(), seen.end(), [](bool value) { return value; });
}

bool ParseLegacyParameters(const Json::Value& array,
                           std::array<Polynomial, kLegacyColorCount>& parameters) {
    if (!array.isArray() || array.size() != kLegacyColorCount) {
        return false;
    }
    // The colour parameter tables store the highest power in Parameter3.
    constexpr const char* kNames[] = {"Parameter3", "Parameter2", "Parameter1", "Parameter0"};
    std::array<bool, kLegacyColorCount> seen{};
    for (const auto& item : array) {
        int32_t color;
        if (!ReadRequiredInt(item, "Color", color) || color < 0 || color >= kLegacyColorCount ||
            seen[color]) {
            return false;
        }
        for (int term = 0; term < Polynomial::SizeAtCompileTime; ++term) {
            if (!ReadNumber(item[kNames[term]], parameters[color][term])) {
                return false;
            }
        }
        seen[color] = true;
    }
    return true;
}

bool ParseLegacyGreyScale(const Json::Value& array,
                          std::array<GreyScale, kChannelCount>& grey_scale) {
    if (!array.isArray() || array.size() != kChannelCount) {
        return false;
    }
    constexpr const char* kNames[] = {"RGreyscale", "GGreyscale", "BGreyscale"};
    std::array<bool, kChannelCount> seen{};
    for (const auto& item : array) {
        int32_t channel;
        if (!ReadRequiredInt(item, "Channel", channel) || channel < 0 || channel >= kChannelCount ||
            seen[channel]) {
            return false;
        }
        for (int color = 0; color < GreyScale::SizeAtCompileTime; ++color) {
            if (!ReadNumber(item[kNames[color]], grey_scale[channel][color])) {
                return false;
            }
        }
        seen[channel] = true;
    }
    return true;
}

// Unlike the colour parameter tables, Parameter0 is the highest power here.
bool ParseLegacyLinearity(const Json::Value& array,
                          std::vector<std::array<Polynomial, kChannelCount>>& linearity) {
    if (!array.isArray() || array.empty()) {
        return false;
    }
    constexpr const char* kNames[] = {"Parameter0", "Parameter1", "Parameter2", "Parameter3"};
    linearity.assign(array.size(), {});
    std::vector<std::array<bool, kChannelCount>> seen(array.size());
    for (const auto& item : array) {
        int32_t level;
        if (!ReadRequiredInt(item, "Function", level) || level < 0 || level >= array.size() ||
            !item["LinearityParameter"].isArray()) {
            return false;
        }
        for (const auto& entry : item["LinearityParameter"]) {
            int32_t channel;
            if (!ReadRequiredInt(entry, "Channel", channel) || channel < 0 ||
                channel >= kChannelCount || seen[level][channel]) {
                return false;
            }
            for (int term = 0; term < Polynomial::SizeAtCompileTime; ++term) {
                if (!ReadNumber(entry[kNames[term]], linearity[level][channel][term])) {
                    return false;
                }
            }
            seen[level][channel] = true;
        }
    }
    for (const auto& level : seen) {
        if (!std::all_of(level.begin(), level.end(), [](bool value) { return value; })) {
            return false;
        }
    }
    return true;
}

// One table set of a pre-V2.1 profile: the unprefixed tables, or the M_ / L_ ones.
bool ParseLegacyTables(const Json::Value& root, const std::string& prefix, int mode,
                       FusionConfig& config, LegacyModel& model) {
    constexpr const char* kParameterTables[] = {"RParameters", "GParameters", "BParameters",
                                                "CParameters"};
    constexpr std::array<const char*, kIrBandCount> kLuxTables = {"LuxCoeffLIR", "LuxCoeffHIR",
                                                                  "LuxCoeffSuperHIR"};
    std::vector<ValueRange> brightness;
    std::array<std::vector<Channels>, kIrBandCount> tables;
    if (!ParseRanges(root[prefix + "IRBrightness"], "BrightnessMin", "BrightnessMax", brightness) ||
        !ParseLegacyGreyScale(root[prefix + "GreyScale"], model.grey_scale[mode])) {
        return false;
    }
    for (int channel = 0; channel < kChannelCount; ++channel) {
        if (!ParseLegacyParameters(root[prefix + kParameterTables[channel]],
                                   model.parameters[mode][channel])) {
            return false;
        }
    }
    for (int band = 0; band < kIrBandCount; ++band) {
        if (!ParseLuxCoefficients(root[prefix + kLuxTables[band]], tables[band])) {
            return false;
        }
    }

    // The selectors are concatenated in brightness order, so one lookup finds mode and level.
    config.ir_brightness.insert(config.ir_brightness.end(), brightness.begin(), brightness.end());
    for (int band = 0; band < kIrBandCount; ++band) {
        auto& coefficients = config.lux_coefficients[band];
        coefficients.insert(coefficients.end(), tables[band].begin(), tables[band].end());
    }
    return true;
}

bool ParseLegacyModel(const Json::Value& root, FusionConfig& config, std::string& error) {
    const auto& brightnesses = root["FusionLightBrightnesses"];
    LegacyModel model;
    if (!brightnesses.isArray() ||
        (brightnesses.size() != 1 && brightnesses.size() != kLegacyModeCount)) {
        error = "invalid FusionLightBrightnesses";
        return false;
    }
    model.mode_count = brightnesses.size() == kLegacyModeCount ? kLegacyModeCount : 1;
    std::array<bool, kLegacyModeCount> seen{};
    for (const auto& item : brightnesses) {
        int32_t level;
        if (!ReadRequiredInt(item, "Level", level) || level < 0 || level >= model.mode_count ||
            seen[level] || !ReadRequiredInt(item, "Brightness", model.mode_brightness[level])) {
            error = "invalid FusionLightBrightnesses";
            return false;
        }
        seen[level] = true;
    }

    // Stock only switches table sets when all three modes are defined; parse dimmest first.
    constexpr std::array<const char*, kLegacyModeCount> kPrefixes = {"L_", "M_", ""};
    constexpr std::array<int, kLegacyModeCount> kModes = {2, 1, 0};
    for (int index = kLegacyModeCount - model.mode_count; index < kLegacyModeCount; ++index) {
        if (!ParseLegacyTables(root, kPrefixes[index], kModes[index], config, model)) {
            error = android::base::StringPrintf("invalid %sleakage model", kPrefixes[index]);
            return false;
        }
    }

    if (!ParseRanges(root["LinearityBrightnessRange"], "BrightnessMin", "BrightnessMax",
                     config.linearity_brightness) ||
        !ParseLegacyLinearity(root["Linearity"], config.linearity) ||
        config.linearity.size() != config.linearity_brightness.size()) {
        error = "invalid Linearity";
        return false;
    }
    if (!ParseRanges(root["IRThreshold"], "IR_Ratio_Min", "IR_Ratio_Max", config.ir_thresholds) ||
        config.ir_thresholds.size() != kIrBandCount) {
        error = "invalid IRThreshold";
        return false;
    }
    for (int band = 0; band < kIrBandCount; ++band) {
        if (config.lux_coefficients[band].size() < config.ir_brightness.size()) {
            error = "coefficient table does not cover its selector";
            return false;
        }
    }
    config.legacy = std::move(model);
    return true;
}

bool ParseV2_1Model(const Json::Value& root, FusionConfig& config, std::string& error) {
    if (!ParseRanges(root["LinearityBrightnessRange"], "BrightnessMin", "BrightnessMax",
                     config.linearity_brightness) ||
        !ParseRanges(root["IRBrightness_V2_1"], "BrightnessMin", "BrightnessMax",
                     config.ir_brightness) ||
        !ParseRanges(root["IRThreshold_V2_1"], "IR_Ratio_Min", "IR_Ratio_Max",
                     config.ir_thresholds) ||
        !ParseChannelModels(root["LinearityCompensation"], config.linearity) ||
        !ParseChannelModels(root["LightLeakageCalculation"], config.leakage) ||
        !ParseChannelModels(root["LightLeakageRatio"], config.leakage_ratio) ||
        !ParseGolden(root["LightLeakageGolden"], config.leakage_golden)) {
        error = "invalid V2.1 model";
        return false;
    }

    constexpr std::array<const char*, kIrBandCount> kDefaultTables = {
            "LuxCoeffLIR_V2_1", "LuxCoeffHIR_V2_1", "LuxCoeffSuperHIR_V2_1"};
    for (int band = 0; band < kIrBandCount; ++band) {
        if (!ParseLuxCoefficients(root[kDefaultTables[band]], config.lux_coefficients[band])) {
            error = "invalid normal lux coefficient table";
            return false;
        }
    }

    const size_t levels = config.linearity_brightness.size();
    if (levels == 0 || config.linearity.size() != levels || config.leakage.size() != levels ||
        config.leakage_ratio.size() != levels || config.leakage_golden.size() != levels ||
        config.ir_thresholds.size() != kIrBandCount || config.ir_brightness.empty()) {
        error = "inconsistent profile dimensions";
        return false;
    }
    for (int band = 0; band < kIrBandCount; ++band) {
        if (config.lux_coefficients[band].size() < config.ir_brightness.size()) {
            error = "coefficient table does not cover its selector";
            return false;
        }
    }
    return true;
}

// The channel-count and screen-off tables use the same keys in both profile formats.
bool ParseSharedTables(const Json::Value& root, FusionConfig& config, std::string& error) {
    constexpr std::array<const char*, kIrBandCount> kCountTables = {
            "LuxCoeffLirChCountPolicy", "LuxCoeffHirChCountPolicy",
            "LuxCoeffSuperHirChCountPolicy"};
    constexpr std::array<const char*, kIrBandCount> kScreenOffTables = {
            "LuxCoeffLirScreenOff", "LuxCoeffHirScreenOff", "LuxCoeffSuperHirScreenOff"};
    for (int band = 0; band < kIrBandCount; ++band) {
        if (config.channel_count_policy_supported &&
            !ParseLuxCoefficients(root[kCountTables[band]],
                                  config.channel_count_coefficients[band])) {
            error = "invalid channel-count coefficient table";
            return false;
        }
        if (config.screen_off_lux_supported &&
            !ParseLuxCoefficients(root[kScreenOffTables[band]],
                                  config.screen_off_coefficients[band])) {
            error = "invalid screen-off coefficient table";
            return false;
        }
    }

    if (config.channel_count_policy_supported &&
        !ParseChannelThresholds(root["ChannelCountThreshold"], config.channel_thresholds)) {
        error = "invalid channel-count thresholds";
        return false;
    }
    if (config.screen_off_lux_supported &&
        !ParseCZeroThresholds(root["CZeroThreshold"], config.c_zero_thresholds)) {
        error = "invalid C-zero thresholds";
        return false;
    }

    for (int band = 0; band < kIrBandCount; ++band) {
        if ((config.channel_count_policy_supported &&
             config.channel_count_coefficients[band].size() < config.channel_thresholds.size()) ||
            (config.screen_off_lux_supported &&
             config.screen_off_coefficients[band].size() < config.c_zero_thresholds.size())) {
            error = "coefficient table does not cover its selector";
            return false;
        }
    }
    return true;
}

bool ParseConfig(std::istream& stream, FusionConfig& config, std::string& error) {
    Json::CharReaderBuilder builder;
    builder["collectComments"] = false;
    Json::Value root;
    if (!Json::parseFromStream(builder, stream, &root, &error)) {
        return false;
    }

    const auto& common = root["CommonConfig"];
    const auto& crop = common["ScreenShotRect"];
    const auto& resolution = common["ScreenResolution"];
    config.cwb.screenshot_period = kDefaultScreenshotPeriod;
    if (!common.isObject() || !crop.isObject() || !resolution.isObject() ||
        !ReadRequiredInt(crop, "LeftTopX", config.cwb.crop_left) ||
        !ReadRequiredInt(crop, "LeftTopY", config.cwb.crop_top) ||
        !ReadRequiredInt(crop, "RightBottomX", config.cwb.crop_right) ||
        !ReadRequiredInt(crop, "RightBottomY", config.cwb.crop_bottom) ||
        !ReadRequiredInt(resolution, "Width", config.cwb.reference_width) ||
        !ReadRequiredInt(resolution, "Height", config.cwb.reference_height) ||
        (common.isMember("CWBScreenshotPeriod") &&
         !ReadRequiredDuration(common, "CWBScreenshotPeriod", config.cwb.screenshot_period))) {
        error = "invalid CommonConfig";
        return false;
    }
    if (config.cwb.crop_left < 0 || config.cwb.crop_top < 0 ||
        config.cwb.crop_right <= config.cwb.crop_left ||
        config.cwb.crop_bottom <= config.cwb.crop_top ||
        config.cwb.crop_right > config.cwb.reference_width ||
        config.cwb.crop_bottom > config.cwb.reference_height ||
        config.cwb.screenshot_period <= 0ms) {
        error = "inconsistent screenshot geometry";
        return false;
    }

    config.fusion_rgb_supported = common["FusionRGBSupported"].asBool();
    config.cwb_supported = common["CWBSupported"].asBool();
    config.cwb.screenshot_weighted = common["SupportCWBScreenshotWeighted"].asBool();
    config.screenshot_v2_1_supported = common["SupportScreenshotAlgorithm_V2_1"].asBool();
    config.screen_off_lux_supported = common["ScreenOffCalLuxSupported"].asBool();
    config.channel_count_policy_supported = common["ChannelCountPolicySupported"].asBool();

    const bool model_parsed = config.screenshot_v2_1_supported
                                      ? ParseV2_1Model(root, config, error)
                                      : ParseLegacyModel(root, config, error);
    return model_parsed && ParseSharedTables(root, config, error);
}

void LoadCwbWeights(CwbConfig& config) {
    if (!config.screenshot_weighted) {
        return;
    }

    std::ifstream stream(kCwbWeightsPath);
    Json::CharReaderBuilder builder;
    builder["collectComments"] = false;
    Json::Value root;
    std::string error;
    int32_t count;
    if (!stream.is_open() || !Json::parseFromStream(builder, stream, &root, &error) ||
        !ReadInt(root["count"], count) || count <= 0 || !root["Weights"].isArray() ||
        root["Weights"].size() != count ||
        count != (config.crop_right - config.crop_left) * (config.crop_bottom - config.crop_top)) {
        LOG(WARNING) << "Invalid CWB weights in " << kCwbWeightsPath
                     << "; using unweighted sampling";
        config.screenshot_weighted = false;
        return;
    }

    config.weights.resize(count);
    for (int index = 0; index < count; ++index) {
        if (!ReadInt(root["Weights"][index], config.weights[index]) || config.weights[index] < 0) {
            LOG(WARNING) << "Invalid CWB weight " << index << " in " << kCwbWeightsPath
                         << "; using unweighted sampling";
            config.weights.clear();
            config.screenshot_weighted = false;
            return;
        }
    }
    LOG(INFO) << "Loaded " << config.weights.size() << " CWB weights from " << kCwbWeightsPath;
}

std::string ToLower(std::string value) {
    std::transform(value.begin(), value.end(), value.begin(),
                   [](unsigned char character) { return std::tolower(character); });
    return value;
}

int32_t GetPanelId(const std::string& panel_name) {
    const std::string lower_name = ToLower(panel_name);
    if (lower_name.find("samsung") != std::string::npos) {
        return 0;
    }
    if (lower_name.find("boe") != std::string::npos) {
        return 1;
    }
    if (panel_name.find("TM") != std::string::npos ||
        lower_name.find("tianma") != std::string::npos) {
        return 2;
    }
    if (lower_name.rfind("visionox", 0) == 0) {
        return 3;
    }
    if (panel_name.find("HX") != std::string::npos) {
        return 4;
    }

    const size_t first_separator = panel_name.find('_');
    if (first_separator == std::string::npos || panel_name.substr(0, first_separator) != "P") {
        return -1;
    }
    const size_t second_separator = panel_name.find('_', first_separator + 1);
    const std::string manufacturer = panel_name.substr(
            first_separator + 1, second_separator == std::string::npos
                                         ? std::string::npos
                                         : second_separator - first_separator - 1);
    int32_t panel_id;
    if (manufacturer == "1") {
        panel_id = 0;
    } else if (manufacturer == "3") {
        panel_id = 1;
    } else if (manufacturer == "7") {
        panel_id = 2;
    } else if (manufacturer == "B") {
        panel_id = 3;
    } else if (manufacturer == "D") {
        panel_id = 4;
    } else {
        return -1;
    }

    if (second_separator == std::string::npos ||
        panel_name.find('_', second_separator + 1) != std::string::npos) {
        return panel_id;
    }
    int32_t version;
    if (!android::base::ParseInt(panel_name.substr(second_separator + 1), &version, 1, 255)) {
        return panel_id;
    }
    return (panel_id << 8) | version;
}

int32_t GetFusionSensorModuleId(const std::string& sensor_name) {
    const std::string lower_name = ToLower(sensor_name);
    constexpr std::pair<const char*, int32_t> kModuleIds[] = {
            {"tcs3720", 3}, {"sip3625", 4},  {"sip3635", 5},  {"stk36c01", 6},
            {"tcs3743", 8}, {"tcs3760", 10}, {"stk3bfx", 11}, {"stk32f02", 12},
    };
    for (const auto& [name, module_id] : kModuleIds) {
        if (lower_name.find(name) != std::string::npos) {
            return module_id;
        }
    }
    return 3;
}

struct MainPanelConfig {
    int32_t panel_id;
    int32_t panel_stage;
};

std::optional<MainPanelConfig> GetMainPanelConfig() {
    const auto service = GetService<IDisplayPanelFeature>();
    if (service == nullptr) {
        LOG(ERROR) << "IDisplayPanelFeature is unavailable";
        return std::nullopt;
    }

    std::vector<std::string> panel_info;
    int32_t return_value = -1;
    const auto info_status =
            service->getDisplayPanelInfo(kPanelInfoFeature, &panel_info, &return_value);
    if (!info_status.isOk()) {
        LOG(ERROR) << "IDisplayPanelFeature::getDisplayPanelInfo failed: "
                   << info_status.getDescription();
        return std::nullopt;
    }
    LOG(INFO) << "IDisplayPanelFeature::getDisplayPanelInfo returned " << return_value << " with "
              << panel_info.size() << " values: [" << android::base::Join(panel_info, ", ") << "]";
    if (return_value != 0 || panel_info.size() < 2) {
        LOG(ERROR) << "Invalid main display panel information";
        return std::nullopt;
    }

    const int32_t panel_id = GetPanelId(panel_info[1]);
    if (panel_id < 0) {
        LOG(ERROR) << "Unknown main display panel " << panel_info[1];
        return std::nullopt;
    }
    LOG(INFO) << "Resolved main display panel " << panel_info[1] << " to ID " << panel_id;

    int32_t panel_stage = kDefaultPanelStage;
    std::vector<int32_t> stage_values;
    return_value = -1;
    const auto stage_status = service->getDisplayPanelFeatureValue(kMainPanelStageFeature,
                                                                   &stage_values, &return_value);
    if (!stage_status.isOk()) {
        LOG(WARNING) << "IDisplayPanelFeature::getDisplayPanelFeatureValue failed: "
                     << stage_status.getDescription() << "; using " << kDefaultPanelStage;
    } else {
        LOG(INFO) << "IDisplayPanelFeature::getDisplayPanelFeatureValue returned " << return_value
                  << " with " << stage_values.size() << " values: ["
                  << android::base::Join(stage_values, ", ") << "]";
        if (return_value == 0 && !stage_values.empty()) {
            panel_stage = stage_values.front();
            LOG(INFO) << "Main display panel stage is " << panel_stage;
        } else {
            LOG(WARNING) << "Invalid main display panel stage; using " << kDefaultPanelStage;
        }
    }
    return MainPanelConfig{panel_id, panel_stage};
}

std::string MakeProfilePath(int32_t panel_id, int32_t module_id,
                            std::optional<int32_t> panel_stage) {
    if (panel_stage.has_value()) {
        return android::base::StringPrintf(
                "/odm/etc/fusionlight_profile/fusionlight_Main_%X_%X_%X.json", panel_id, module_id,
                *panel_stage);
    }
    return android::base::StringPrintf("/odm/etc/fusionlight_profile/fusionlight_Main_%X_%X.json",
                                       panel_id, module_id);
}

std::optional<std::string> FindProfilePath(int32_t panel_id, int32_t module_id,
                                           int32_t panel_stage) {
    if (panel_stage >= 0 && panel_stage <= 4) {
        const std::string staged_path = MakeProfilePath(panel_id, module_id, panel_stage);
        if (access(staged_path.c_str(), F_OK) == 0) {
            return staged_path;
        }
    }

    const std::string path = MakeProfilePath(panel_id, module_id, std::nullopt);
    return access(path.c_str(), F_OK) == 0 ? std::make_optional(path) : std::nullopt;
}

const Json::Value* FindMember(const Json::Value& value, const std::string& name) {
    if (!value.isObject()) {
        return nullptr;
    }
    if (value.isMember(name)) {
        return &value[name];
    }
    for (const auto& member_name : value.getMemberNames()) {
        if (const auto* result = FindMember(value[member_name], name)) {
            return result;
        }
    }
    return nullptr;
}

}  // namespace

std::optional<FusionConfig> LoadConfig(const std::string& sensor_name) {
    const auto panel = GetMainPanelConfig();
    if (!panel.has_value()) {
        return std::nullopt;
    }

    const int32_t module_id = GetFusionSensorModuleId(sensor_name);
    const auto path = FindProfilePath(panel->panel_id, module_id, panel->panel_stage);
    if (!path.has_value()) {
        LOG(ERROR) << "FusionLight profile was not found for panel " << panel->panel_id
                   << ", sensor module " << module_id << ", stage " << panel->panel_stage;
        return std::nullopt;
    }

    std::ifstream stream(*path);
    if (!stream.is_open()) {
        LOG(ERROR) << "Unable to open FusionLight profile " << *path;
        return std::nullopt;
    }
    FusionConfig config;
    std::string error;
    if (!ParseConfig(stream, config, error)) {
        LOG(ERROR) << "Invalid FusionLight profile " << *path << ": " << error;
        return std::nullopt;
    }
    LoadCwbWeights(config.cwb);
    LOG(INFO) << "Loaded FusionLight profile from " << *path;
    return config;
}

std::vector<Channels> LoadCalibration(const FusionConfig& config) {
    std::vector<Channels> calibration(config.leakage_golden.size(), Channels::Ones());
    const auto service = GetService<ISensorFeature>();
    if (service == nullptr) {
        LOG(WARNING) << "ISensorFeature is unavailable; using unit FusionLight calibration";
        return calibration;
    }

    std::string response;
    const auto status = service->getSensorCalibrationData(kHighPwmSensorType, &response);
    if (!status.isOk()) {
        LOG(WARNING) << "ISensorFeature::getSensorCalibrationData failed: "
                     << status.getDescription() << "; using profile golden values";
        return calibration;
    }
    LOG(INFO) << "ISensorFeature::getSensorCalibrationData returned " << response.size()
              << " bytes: " << response;
    if (response.empty() || response == "default") {
        LOG(WARNING) << "No device FusionLight calibration; using profile golden values";
        return calibration;
    }

    Json::CharReaderBuilder builder;
    builder["collectComments"] = false;
    std::istringstream stream(response);
    Json::Value root;
    std::string error;
    if (!Json::parseFromStream(builder, stream, &root, &error)) {
        LOG(WARNING) << "Invalid device FusionLight calibration: " << error;
        return calibration;
    }

    constexpr const char* kChannelNames[] = {"R", "G", "B", "C"};
    int applied_values = 0;
    for (int level = 0; level < calibration.size(); ++level) {
        for (int channel = 0; channel < kChannelCount; ++channel) {
            const std::string key =
                    "W_VIEW_" + std::string(kChannelNames[channel]) + "_" + std::to_string(level);
            const Json::Value* value = FindMember(root, key);
            const double golden = config.leakage_golden[level][channel];
            double measured;
            if (value != nullptr && ReadNumber(*value, measured) && measured != 0.0 &&
                golden != 0.0) {
                calibration[level][channel] = measured / golden;
                ++applied_values;
            }
        }
    }
    LOG(INFO) << "Applied " << applied_values << " of " << calibration.size() * kChannelCount
              << " FusionLight calibration values";
    return calibration;
}

}  // namespace fusionlight
}  // namespace implementation
}  // namespace subhal
}  // namespace V2_1
}  // namespace sensors
}  // namespace hardware
}  // namespace android
