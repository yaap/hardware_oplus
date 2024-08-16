#
# Copyright (C) 2022-2024 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

SEPOLICY_PLATFORM := $(subst device/qcom/sepolicy_vndr/,,$(SEPOLICY_PATH))

BOARD_VENDOR_SEPOLICY_DIRS += \
    hardware/oplus/sepolicy/qti/vendor \
    hardware/oplus/sepolicy/qti/vendor/$(SEPOLICY_PLATFORM)

SYSTEM_EXT_PRIVATE_SEPOLICY_DIRS += \
    hardware/oplus/sepolicy/qti/private \
    hardware/oplus/sepolicy/qti/private/$(SEPOLICY_PLATFORM)

SYSTEM_EXT_PUBLIC_SEPOLICY_DIRS += \
    hardware/oplus/sepolicy/qti/public \
    hardware/oplus/sepolicy/qti/public/$(SEPOLICY_PLATFORM)

ifneq ($(SEPOLICY_PLATFORM), legacy-um)
BOARD_VENDOR_SEPOLICY_DIRS += \
    hardware/oplus/sepolicy/qti/vendor/common-um

SYSTEM_EXT_PRIVATE_SEPOLICY_DIRS += \
    hardware/oplus/sepolicy/qti/private/common-um

SYSTEM_EXT_PUBLIC_SEPOLICY_DIRS += \
    hardware/oplus/sepolicy/qti/public/common-um
endif

include device/lineage/sepolicy/libperfmgr/sepolicy.mk

SYSTEM_EXT_PUBLIC_SEPOLICY_DIRS := $(shell echo $(SYSTEM_EXT_PUBLIC_SEPOLICY_DIRS) | tr ' ' '\n' | sort -u | tr '\n' ' ')
SYSTEM_EXT_PRIVATE_SEPOLICY_DIRS := $(shell echo $(SYSTEM_EXT_PRIVATE_SEPOLICY_DIRS) | tr ' ' '\n' | sort -u | tr '\n' ' ')

PRODUCT_PUBLIC_SEPOLICY_DIRS := $(shell echo $(PRODUCT_PUBLIC_SEPOLICY_DIRS) | tr ' ' '\n' | sort -u | tr '\n' ' ')
PRODUCT_PRIVATE_SEPOLICY_DIRS := $(shell echo $(PRODUCT_PRIVATE_SEPOLICY_DIRS) | tr ' ' '\n' | sort -u | tr '\n' ' ')

BOARD_VENDOR_SEPOLICY_DIRS := $(shell echo $(BOARD_VENDOR_SEPOLICY_DIRS) | tr ' ' '\n' | sort -u | tr '\n' ' ')
