package com.oplus.os;

import android.os.IVibratorManagerService;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Slog;
import java.util.Objects;

/* loaded from: classes.dex */
public final class WaveformEffect implements Parcelable {
    public static final Parcelable.Creator<WaveformEffect> CREATOR = new Parcelable.Creator<WaveformEffect>() { // from class: com.oplus.os.WaveformEffect.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WaveformEffect createFromParcel(Parcel p) {
            return new WaveformEffect(p);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WaveformEffect[] newArray(int size) {
            return new WaveformEffect[size];
        }
    };
    public static final int EFFECT_AFGAME_DOUBLE_KILL = 76;
    public static final int EFFECT_AFGAME_NORMAL_KILL = 75;
    public static final int EFFECT_AFGAME_PANTA_KILL = 79;
    public static final int EFFECT_AFGAME_TRIPLE_KILL = 77;
    public static final int EFFECT_AFGAME_ULTRA_KILL = 78;
    public static final int EFFECT_ALARM_ALARM_CLOCK = 238;
    public static final int EFFECT_ALARM_BEEP = 239;
    public static final int EFFECT_ALARM_BREEZE = 240;
    public static final int EFFECT_ALARM_CLOUDSCAPE = 341;
    public static final int EFFECT_ALARM_DAWN = 241;
    public static final int EFFECT_ALARM_DREAM = 242;
    public static final int EFFECT_ALARM_FLUTTERING = 243;
    public static final int EFFECT_ALARM_FLYER = 244;
    public static final int EFFECT_ALARM_GOODENERGY = 342;
    public static final int EFFECT_ALARM_INTERESTING = 245;
    public static final int EFFECT_ALARM_IN_GAME_ALARM = 364;
    public static final int EFFECT_ALARM_LEISURELY = 246;
    public static final int EFFECT_ALARM_MEMORY = 247;
    public static final int EFFECT_ALARM_PACMAN = 317;
    public static final int EFFECT_ALARM_RELIEVED = 248;
    public static final int EFFECT_ALARM_RIPPLE = 249;
    public static final int EFFECT_ALARM_SLOWLY = 250;
    public static final int EFFECT_ALARM_SPRING = 251;
    public static final int EFFECT_ALARM_STARS = 252;
    public static final int EFFECT_ALARM_SURGING = 253;
    public static final int EFFECT_ALARM_TACTFULLY = 254;
    public static final int EFFECT_ALARM_THE_WIND = 255;
    public static final int EFFECT_ALARM_WAKE_UP_SAMURAI = 363;
    public static final int EFFECT_ALARM_WALKING_IN_THE_RAIN = 256;
    public static final int EFFECT_ALARM_WEATHER_CLOUDY = 145;
    public static final int EFFECT_ALARM_WEATHER_DEFAULT = 147;
    public static final int EFFECT_ALARM_WEATHER_RAIN = 151;
    public static final int EFFECT_ALARM_WEATHER_SMOG = 149;
    public static final int EFFECT_ALARM_WEATHER_SNOW = 150;
    public static final int EFFECT_ALARM_WEATHER_SUNNY = 148;
    public static final int EFFECT_ALARM_WEATHER_THUNDERSTORM = 146;
    public static final int EFFECT_ALARM_WEATHER_WIND = 144;
    public static final int EFFECT_ALERTSLIDER_DOWN = 308;
    public static final int EFFECT_ARTIST_ALARM = 313;
    public static final int EFFECT_ARTIST_NOTIFICATION = 310;
    public static final int EFFECT_ARTIST_RINGTONE = 311;
    public static final int EFFECT_ARTIST_TEXT = 312;
    public static final int EFFECT_CANYON_CALL = 351;
    public static final int EFFECT_CHASE = 269;
    public static final int EFFECT_CLEAR_NOTIFICATION_HISTORY = 471;
    public static final int EFFECT_CLIMBER = 268;
    public static final int EFFECT_CUSTOMIZED_ATTACH_TO_MIDDLE = 73;
    public static final int EFFECT_CUSTOMIZED_BREATHE_SPREAD_OUT = 74;
    public static final int EFFECT_CUSTOMIZED_CONFLICT = 52;
    public static final int EFFECT_CUSTOMIZED_CONVERGE = 51;
    public static final int EFFECT_CUSTOMIZED_LONG_VIBRATE = 70;
    public static final int EFFECT_CUSTOMIZED_RUSH_LEFT_TO_RIGHT = 53;
    public static final int EFFECT_CUSTOMIZED_SPREAD_OUT = 50;
    public static final int EFFECT_CUSTOMIZED_STRONG_GRANULAR = 69;
    public static final int EFFECT_CUSTOMIZED_STRONG_ONE_SENCOND = 72;
    public static final int EFFECT_CUSTOMIZED_STRONG_POINTFOUR_SENCOND = 71;
    public static final int EFFECT_CUSTOMIZED_THREE_DIMENSION_TOUCH = 49;
    public static final int EFFECT_CUSTOMIZED_WEAK_GRANULAR = 68;
    private static final int EFFECT_DURATION_ENROLL_FINGERPRINT_SUCCESS = 242;
    private static final int EFFECT_DURATION_LOCKSCREEN_FEEDBACK = 115;
    private static final int EFFECT_DURATION_MESSAGE_HEARTBEAT = 470;
    private static final int EFFECT_DURATION_MESSAGE_RAPID = 430;
    private static final int EFFECT_DURATION_MESSAGE_REMIND = 330;
    private static final int EFFECT_DURATION_MESSAGE_STREAK = 390;
    private static final int EFFECT_DURATION_MESSAGE_SYMPHONIC = 580;
    private static final int EFFECT_DURATION_SCREENSHOT_FEEDBACK = 147;
    private static final int EFFECT_DURATION_THREE_STAGE_KEY = 220;
    public static final int EFFECT_EMULATION_KEYBOARD_DOWN = 302;
    public static final int EFFECT_EMULATION_KEYBOARD_UP = 303;
    public static final int EFFECT_ENROLL_FINGERPRINT_SUCCESS = 407;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_1 = 476;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_2 = 480;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_ATTACK = 477;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_ATTACK2 = 483;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_CONTINUOUS_STEPS = 486;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_CRISP = 473;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_CRISP_LONG = 485;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_LONG = 478;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_MICROWAVE_RIPPLES = 484;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_ORDERLY = 488;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_ROUNDABOUT = 479;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_SPORT = 481;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_STEPS = 474;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_STRONG = 489;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_STRONG_ATTACK = 482;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_STRONG_RHYTHM = 487;
    public static final int EFFECT_GAME_CUSTOM_VIBRATION_WEAK = 475;
    public static final int EFFECT_ID_THREE_STAGE_KEY = 430;
    public static final int EFFECT_INPUT_METHOD_LONG_PRESS = 490;
    public static final int EFFECT_INVALID = -1;
    public static final int EFFECT_LOCKSCREEN_FEEDBACK = 406;
    private static final int EFFECT_MESSAGE_HEARTBEAT = 398;
    private static final int EFFECT_MESSAGE_RAPID = 400;
    private static final int EFFECT_MESSAGE_REMIND = 399;
    private static final int EFFECT_MESSAGE_STREAK = 397;
    private static final int EFFECT_MESSAGE_SYMPHONIC = 396;
    public static final int EFFECT_MODERATE_SHORT_VIBRATE_ONCE = 2;
    public static final int EFFECT_MODERATE_SHORT_VIBRATE_TRIPLE = 3;
    public static final int EFFECT_MODERATE_SHORT_VIBRATE_TWICE = 3;
    public static final int EFFECT_MYSTIC_STORE = 352;
    public static final int EFFECT_NOTIFICATION_ALLAY = 214;
    public static final int EFFECT_NOTIFICATION_ALLUSION = 215;
    public static final int EFFECT_NOTIFICATION_AMIABLE = 216;
    public static final int EFFECT_NOTIFICATION_AUDITION = 366;
    public static final int EFFECT_NOTIFICATION_BEATING = 334;
    private static final int EFFECT_NOTIFICATION_BEATING_2 = 390;
    public static final int EFFECT_NOTIFICATION_BLARE = 217;
    public static final int EFFECT_NOTIFICATION_BLINK = 343;
    public static final int EFFECT_NOTIFICATION_BLISSFUL = 218;
    public static final int EFFECT_NOTIFICATION_BLOCK = 338;
    private static final int EFFECT_NOTIFICATION_BLOCK_2 = 394;
    public static final int EFFECT_NOTIFICATION_BOWLBELL = 330;
    private static final int EFFECT_NOTIFICATION_BOWLBELL_2 = 386;
    public static final int EFFECT_NOTIFICATION_BRISK = 219;
    public static final int EFFECT_NOTIFICATION_BUBBLE = 220;
    public static final int EFFECT_NOTIFICATION_CHEERFUL = 221;
    public static final int EFFECT_NOTIFICATION_CIRCLE = 337;
    public static final int EFFECT_NOTIFICATION_CLEAR = 222;
    public static final int EFFECT_NOTIFICATION_COMELY = 223;
    public static final int EFFECT_NOTIFICATION_COZY = 224;
    public static final int EFFECT_NOTIFICATION_CRYSTALCLEAR = 129;
    private static final int EFFECT_NOTIFICATION_CRYSTALCLEAR_2 = 371;
    public static final int EFFECT_NOTIFICATION_DING = 225;
    public static final int EFFECT_NOTIFICATION_EFFERVESCE = 226;
    public static final int EFFECT_NOTIFICATION_ELEGANT = 227;
    public static final int EFFECT_NOTIFICATION_EMERGE = 131;
    private static final int EFFECT_NOTIFICATION_EMERGE_2 = 373;
    public static final int EFFECT_NOTIFICATION_FREE = 228;
    public static final int EFFECT_NOTIFICATION_FUN = 139;
    private static final int EFFECT_NOTIFICATION_FUN_2 = 381;
    public static final int EFFECT_NOTIFICATION_GRANULES = 143;
    private static final int EFFECT_NOTIFICATION_GRANULES_2 = 385;
    public static final int EFFECT_NOTIFICATION_HALLUCINATION = 229;
    public static final int EFFECT_NOTIFICATION_HARP = 133;
    private static final int EFFECT_NOTIFICATION_HARP_2 = 375;
    public static final int EFFECT_NOTIFICATION_HEARTBEAT = 6;
    public static final int EFFECT_NOTIFICATION_HEY = 332;
    private static final int EFFECT_NOTIFICATION_HEY_2 = 388;
    public static final int EFFECT_NOTIFICATION_INBOUND = 230;
    public static final int EFFECT_NOTIFICATION_INGENIOUS = 142;
    private static final int EFFECT_NOTIFICATION_INGENIOUS_2 = 384;
    public static final int EFFECT_NOTIFICATION_INSTANT = 138;
    private static final int EFFECT_NOTIFICATION_INSTANT_2 = 380;
    public static final int EFFECT_NOTIFICATION_IN_GAME_SMS = 365;
    private static final int EFFECT_NOTIFICATION_JINGLE_2 = 369;
    public static final int EFFECT_NOTIFICATION_JOY = 136;
    private static final int EFFECT_NOTIFICATION_JOY_2 = 378;
    public static final int EFFECT_NOTIFICATION_LIGHT = 231;
    public static final int EFFECT_NOTIFICATION_MEET = 232;
    public static final int EFFECT_NOTIFICATION_NAIVETY = 233;
    public static final int EFFECT_NOTIFICATION_NEWS = 333;
    private static final int EFFECT_NOTIFICATION_NEWS_2 = 389;
    public static final int EFFECT_NOTIFICATION_ONEPLUS_TWINKLE = 237;
    public static final int EFFECT_NOTIFICATION_OVERTONE = 134;
    private static final int EFFECT_NOTIFICATION_OVERTONE_2 = 376;
    public static final int EFFECT_NOTIFICATION_PACMAN = 316;
    public static final int EFFECT_NOTIFICATION_PERCUSSION = 135;
    private static final int EFFECT_NOTIFICATION_PERCUSSION_2 = 377;
    public static final int EFFECT_NOTIFICATION_QUICKLY = 234;
    public static final int EFFECT_NOTIFICATION_RAPID = 8;
    public static final int EFFECT_NOTIFICATION_RECEIVE = 140;
    private static final int EFFECT_NOTIFICATION_RECEIVE_2 = 382;
    public static final int EFFECT_NOTIFICATION_REMIND = 7;
    public static final int EFFECT_NOTIFICATION_RHYTHM = 235;
    public static final int EFFECT_NOTIFICATION_RIPPLES = 132;
    private static final int EFFECT_NOTIFICATION_RIPPLES_2 = 374;
    public static final int EFFECT_NOTIFICATION_RISE = 331;
    private static final int EFFECT_NOTIFICATION_RISE_2 = 387;
    private static final int EFFECT_NOTIFICATION_ROUND_2 = 393;
    public static final int EFFECT_NOTIFICATION_SHORT = 335;
    private static final int EFFECT_NOTIFICATION_SHORT_2 = 391;
    public static final int EFFECT_NOTIFICATION_SIMPLE = 128;
    private static final int EFFECT_NOTIFICATION_SIMPLE_2 = 370;
    public static final int EFFECT_NOTIFICATION_SPLASH = 141;
    private static final int EFFECT_NOTIFICATION_SPLASH_2 = 383;
    public static final int EFFECT_NOTIFICATION_STREAK = 5;
    public static final int EFFECT_NOTIFICATION_SURPRISE = 236;
    public static final int EFFECT_NOTIFICATION_SYMPHONIC = 4;
    public static final int EFFECT_NOTIFICATION_TOP = 336;
    private static final int EFFECT_NOTIFICATION_TOP_2 = 392;
    public static final int EFFECT_NOTIFICATION_TUNES = 130;
    private static final int EFFECT_NOTIFICATION_TUNES_2 = 372;
    public static final int EFFECT_NOTIFICATION_TWINKLE = 137;
    private static final int EFFECT_NOTIFICATION_TWINKLE_2 = 379;
    public static final int EFFECT_NOTIFICATION_WHOOPDOOP = 344;
    public static final int EFFECT_NOTIFICATION_ZANZA = 339;
    private static final int EFFECT_NOTIFICATION_ZANZA_2 = 395;
    public static final int EFFECT_ONE_KEY_CLEAR_RECENT_TASK = 472;
    private static final int EFFECT_OPLUS_RINGTONE_BATHEEARTH = 441;
    private static final int EFFECT_OPLUS_RINGTONE_BLOW = 460;
    private static final int EFFECT_OPLUS_RINGTONE_CLOUDS = 453;
    private static final int EFFECT_OPLUS_RINGTONE_CRYSTALCLEAR = 464;
    private static final int EFFECT_OPLUS_RINGTONE_DEW = 466;
    private static final int EFFECT_OPLUS_RINGTONE_DISTANT = 436;
    private static final int EFFECT_OPLUS_RINGTONE_DRAGONFLY = 457;
    private static final int EFFECT_OPLUS_RINGTONE_DROPWATER = 458;
    private static final int EFFECT_OPLUS_RINGTONE_ELEGANTQUIET = 444;
    private static final int EFFECT_OPLUS_RINGTONE_FLUCTUATION = 459;
    private static final int EFFECT_OPLUS_RINGTONE_FROST = 468;
    private static final int EFFECT_OPLUS_RINGTONE_FULLMOON = 452;
    private static final int EFFECT_OPLUS_RINGTONE_HAUNTING = 456;
    private static final int EFFECT_OPLUS_RINGTONE_ICESNOW = 447;
    private static final int EFFECT_OPLUS_RINGTONE_INSECTS = 465;
    private static final int EFFECT_OPLUS_RINGTONE_JUNGLEMORNING = 442;
    private static final int EFFECT_OPLUS_RINGTONE_LEAVESLIGHT = 461;
    private static final int EFFECT_OPLUS_RINGTONE_MEDITATE = 435;
    private static final int EFFECT_OPLUS_RINGTONE_MOONLOTUS = 438;
    private static final int EFFECT_OPLUS_RINGTONE_NIGHTSKY = 431;
    private static final int EFFECT_OPLUS_RINGTONE_POND = 437;
    private static final int EFFECT_OPLUS_RINGTONE_RAINDROP = 470;
    private static final int EFFECT_OPLUS_RINGTONE_RAINEVERYTHING = 450;
    private static final int EFFECT_OPLUS_RINGTONE_RAINFOREST = 449;
    private static final int EFFECT_OPLUS_RINGTONE_RAINSPLASH = 469;
    private static final int EFFECT_OPLUS_RINGTONE_RIPPLINGWATER = 439;
    private static final int EFFECT_OPLUS_RINGTONE_SHIMMER = 440;
    private static final int EFFECT_OPLUS_RINGTONE_SHINE = 467;
    private static final int EFFECT_OPLUS_RINGTONE_SILVER = 443;
    private static final int EFFECT_OPLUS_RINGTONE_SNOWFLAKE = 463;
    private static final int EFFECT_OPLUS_RINGTONE_STARACROSS = 451;
    private static final int EFFECT_OPLUS_RINGTONE_STILL = 455;
    private static final int EFFECT_OPLUS_RINGTONE_SUMMERBEACH = 445;
    private static final int EFFECT_OPLUS_RINGTONE_SUMMERNIGHT = 446;
    private static final int EFFECT_OPLUS_RINGTONE_THESTARS = 432;
    private static final int EFFECT_OPLUS_RINGTONE_THESUNRISE = 433;
    private static final int EFFECT_OPLUS_RINGTONE_THESUNSET = 434;
    private static final int EFFECT_OPLUS_RINGTONE_WARMSUN = 462;
    private static final int EFFECT_OPLUS_RINGTONE_WINTERSNOW = 448;
    private static final int EFFECT_OPLUS_RINGTONE_WONDERLAND = 454;
    public static final int EFFECT_OTHER_BIG_SCALE = 9;
    public static final int EFFECT_OTHER_BREATH_SIMULATION = 165;
    public static final int EFFECT_OTHER_BULB = 285;
    public static final int EFFECT_OTHER_CHARGING_SIMULATION = 166;
    public static final int EFFECT_OTHER_CLOSE = 274;
    public static final int EFFECT_OTHER_COMPATIBLE_1 = 278;
    public static final int EFFECT_OTHER_COMPATIBLE_2 = 279;
    public static final int EFFECT_OTHER_COMPLETE = 284;
    public static final int EFFECT_OTHER_ELASTICITY = 286;
    public static final int EFFECT_OTHER_FINGERPRINT_CORRECT_EFFECT = 156;
    public static final int EFFECT_OTHER_HALF_LAP = 275;
    public static final int EFFECT_OTHER_KEYBOARD_MEDIUM = 158;
    public static final int EFFECT_OTHER_KEYBOARD_STRONG = 159;
    public static final int EFFECT_OTHER_KEYBOARD_WEAK = 157;
    public static final int EFFECT_OTHER_SMALL_SCALE = 10;
    public static final int EFFECT_OTHER_STRENGTH_LEVEL_BAR_EDGE = 154;
    public static final int EFFECT_OTHER_STYLESWITCH = 280;
    public static final int EFFECT_OTHER_STYLESWITCH_SOFT = 287;
    public static final int EFFECT_OTHER_SUSPENDBUTTON_BOTTOMOUT = 282;
    public static final int EFFECT_OTHER_SUSPENDBUTTON_MENU = 283;
    public static final int EFFECT_OTHER_THREEFINGERS_LONG = 270;
    public static final int EFFECT_OTHER_THREEFINGERS_SCREENSHOT = 272;
    public static final int EFFECT_OTHER_THREEFINGERS_UP = 271;
    public static final int EFFECT_OTHER_TWOFINGERS_DOWN = 276;
    public static final int EFFECT_OTHER_TWOFINGERS_LONG = 277;
    public static final int EFFECT_OTHER_UNFOLD = 273;
    public static final int EFFECT_OTHER_VOICE_ASSISTANT = 167;
    public static final int EFFECT_OTHER_VOICE_LEVEL_BAR_EDGE = 153;
    public static final int EFFECT_OTHER_WATERRIPPLE = 281;
    public static final int EFFECT_PUBG_RIFLE = 11;
    public static final int EFFECT_PUBG_SHORT_GUN = 12;
    public static final int EFFECT_PUBG_SNIPER = 13;
    public static final int EFFECT_RAZER_CLICKY_PRESS = 288;
    public static final int EFFECT_RAZER_LINEAR_PRESS = 289;
    public static final int EFFECT_RECENT_TASK_FEEDBACK = 367;
    public static final int EFFECT_RINGTONE_ALACRITY = 180;
    public static final int EFFECT_RINGTONE_AMENITY = 181;
    public static final int EFFECT_RINGTONE_BLISS = 106;
    public static final int EFFECT_RINGTONE_BLUES = 182;
    public static final int EFFECT_RINGTONE_BOUNCE = 183;
    public static final int EFFECT_RINGTONE_CALM = 100;
    public static final int EFFECT_RINGTONE_CHILDHOOD = 121;
    public static final int EFFECT_RINGTONE_CLASSIC = 108;
    public static final int EFFECT_RINGTONE_CLOUD = 185;
    public static final int EFFECT_RINGTONE_COMMUTE = 123;
    public static final int EFFECT_RINGTONE_CYCLOTRON = 186;
    public static final int EFFECT_RINGTONE_DANCE = 110;
    public static final int EFFECT_RINGTONE_DAZZLE = 119;
    public static final int EFFECT_RINGTONE_DELIGHT = 107;
    public static final int EFFECT_RINGTONE_DISTINCT = 187;
    public static final int EFFECT_RINGTONE_DREAM = 103;
    public static final int EFFECT_RINGTONE_DREAMJAZZ = 321;
    public static final int EFFECT_RINGTONE_DYNAMIC = 188;
    public static final int EFFECT_RINGTONE_EAGER = 209;
    public static final int EFFECT_RINGTONE_EASTERN_TRANQUILITY = 329;
    public static final int EFFECT_RINGTONE_EBULLITION = 210;
    public static final int EFFECT_RINGTONE_ECHO = 189;
    public static final int EFFECT_RINGTONE_ECLUB = 323;
    public static final int EFFECT_RINGTONE_EXPECT = 190;
    public static final int EFFECT_RINGTONE_FAIRVIEWS = 125;
    public static final int EFFECT_RINGTONE_FANATICAL = 191;
    public static final int EFFECT_RINGTONE_FEELING = 345;
    public static final int EFFECT_RINGTONE_FIREFLY = 112;
    public static final int EFFECT_RINGTONE_FRESHMORNING = 325;
    public static final int EFFECT_RINGTONE_FRIENDSHIP = 211;
    public static final int EFFECT_RINGTONE_FUNK = 324;
    public static final int EFFECT_RINGTONE_FUNKY = 192;
    public static final int EFFECT_RINGTONE_GAZINGOUT = 118;
    public static final int EFFECT_RINGTONE_GUITAR = 193;
    public static final int EFFECT_RINGTONE_HARPING = 194;
    public static final int EFFECT_RINGTONE_HIGHLIGHT = 195;
    public static final int EFFECT_RINGTONE_HOLIDAY = 326;
    public static final int EFFECT_RINGTONE_HOUSEMUSIC = 322;
    public static final int EFFECT_RINGTONE_INNOCENCE = 197;
    public static final int EFFECT_RINGTONE_IN_GAME_RINGTON = 361;
    public static final int EFFECT_RINGTONE_JAZZ_LIFE = 212;
    public static final int EFFECT_RINGTONE_JOURNEY = 198;
    public static final int EFFECT_RINGTONE_JOYOUS = 199;
    public static final int EFFECT_RINGTONE_LAKESIDE = 117;
    public static final int EFFECT_RINGTONE_LAZY = 200;
    public static final int EFFECT_RINGTONE_LDYL = 196;
    public static final int EFFECT_RINGTONE_LONGING = 105;
    public static final int EFFECT_RINGTONE_MARIMBA = 201;
    public static final int EFFECT_RINGTONE_MEMORIES = 115;
    public static final int EFFECT_RINGTONE_MODERN = 328;
    public static final int EFFECT_RINGTONE_MYSTICAL = 202;
    public static final int EFFECT_RINGTONE_NATURE = 346;
    public static final int EFFECT_RINGTONE_NIGHT = 116;
    public static final int EFFECT_RINGTONE_NOSTALGIC = 101;
    public static final int EFFECT_RINGTONE_NOVIBRATE = 67;
    public static final int EFFECT_RINGTONE_OLD_TELEPHONE = 203;
    public static final int EFFECT_RINGTONE_ONEPLUS_CALM = 184;
    public static final int EFFECT_RINGTONE_ONEPLUS_TUNE = 204;
    public static final int EFFECT_RINGTONE_OPTIMISTIC = 206;
    public static final int EFFECT_RINGTONE_PACMAN = 315;
    public static final int EFFECT_RINGTONE_PIANO = 207;
    public static final int EFFECT_RINGTONE_PLAYPARK = 109;
    public static final int EFFECT_RINGTONE_PURE = 127;
    public static final int EFFECT_RINGTONE_REALME_ITSREALME = 82;
    public static final int EFFECT_RINGTONE_REALME_JINGLE = 81;
    public static final int EFFECT_RINGTONE_REALME_TUNE = 80;
    public static final int EFFECT_RINGTONE_RELAX = 120;
    public static final int EFFECT_RINGTONE_RHYTHM = 205;
    public static final int EFFECT_RINGTONE_RM_ITSRM = 82;
    public static final int EFFECT_RINGTONE_RM_JINGLE = 81;
    public static final int EFFECT_RINGTONE_RM_TUNE = 80;
    public static final int EFFECT_RINGTONE_ROCK = 360;
    public static final int EFFECT_RINGTONE_ROMANCE = 102;
    public static final int EFFECT_RINGTONE_SCHOOL = 122;
    public static final int EFFECT_RINGTONE_SILENCE = 114;
    public static final int EFFECT_RINGTONE_SOLITUDE = 126;
    public static final int EFFECT_RINGTONE_STARS = 113;
    public static final int EFFECT_RINGTONE_SUMMER = 124;
    public static final int EFFECT_RINGTONE_SUN_GLITTERING = 213;
    public static final int EFFECT_RINGTONE_TEMPLESOUND = 320;
    public static final int EFFECT_RINGTONE_TRINKETS = 111;
    public static final int EFFECT_RINGTONE_T_JINGLE = 307;
    public static final int EFFECT_RINGTONE_VISIONS = 104;
    public static final int EFFECT_RINGTONE_WATERCICADE = 327;
    public static final int EFFECT_RINGTONE_WHIRL = 208;
    public static final int EFFECT_RM_SOGOU_KEYBOARD_TURN_ON_VIBRATION = 568;
    public static final int EFFECT_SCREENSHOT_FEEDBACK = 409;
    public static final int EFFECT_SCROLL_ON_TIME_PICKER = 408;
    public static final int EFFECT_SGAME_2ND_DOUBLE_KILL_CRISP = 492;
    public static final int EFFECT_SGAME_2ND_DOUBLE_KILL_SOFT = 520;
    public static final int EFFECT_SGAME_2ND_GAME_FAILED_CRISP = 497;
    public static final int EFFECT_SGAME_2ND_GAME_FAILED_SOFT = 525;
    public static final int EFFECT_SGAME_2ND_GAME_START_CRISP = 496;
    public static final int EFFECT_SGAME_2ND_GAME_START_SOFT = 524;
    public static final int EFFECT_SGAME_2ND_GAME_VICTORY_CRISP = 498;
    public static final int EFFECT_SGAME_2ND_GAME_VICTORY_SOFT = 526;
    public static final int EFFECT_SGAME_2ND_NORMAL_KILL_CRISP = 491;
    public static final int EFFECT_SGAME_2ND_NORMAL_KILL_SOFT = 519;
    public static final int EFFECT_SGAME_2ND_PENTA_KILL_CRISP = 495;
    public static final int EFFECT_SGAME_2ND_PENTA_KILL_SOFT = 523;
    public static final int EFFECT_SGAME_2ND_QUAD_KILL_CRISP = 494;
    public static final int EFFECT_SGAME_2ND_QUAD_KILL_SOFT = 522;
    public static final int EFFECT_SGAME_2ND_TRIPLE_KILL_CRISP = 493;
    public static final int EFFECT_SGAME_2ND_TRIPLE_KILL_SOFT = 521;
    public static final int EFFECT_SGAME_DOUBLE_KILL = 55;
    public static final int EFFECT_SGAME_FIRST_BLOOD = 54;
    public static final int EFFECT_SGAME_GODLIKE = 62;
    public static final int EFFECT_SGAME_KILLING_SPREE = 59;
    public static final int EFFECT_SGAME_LEGENDARY = 63;
    public static final int EFFECT_SGAME_PANTA_KILL = 58;
    public static final int EFFECT_SGAME_RAMPAGE = 60;
    public static final int EFFECT_SGAME_TRIBLE_KILL = 56;
    public static final int EFFECT_SGAME_ULTRA_KILL = 57;
    public static final int EFFECT_SGAME_UNSTOPPABLE = 61;
    public static final int EFFECT_TELCEL_RIE = 350;
    public static final int EFFECT_VIBRATE_WITH_GT = 314;
    public static final int EFFECT_VIBRATE_WITH_RINGTONE = 64;
    public static final int EFFECT_VIRTUAL_KEY_FEEDBACK = 368;
    public static final int EFFECT_WEAKEST_SHORT_VIBRATE_ONCE = 0;
    public static final int EFFECT_WEAK_EMULATION_KEYBOARD_DOWN = 304;
    public static final int EFFECT_WEAK_EMULATION_KEYBOARD_UP = 305;
    public static final int EFFECT_WEAK_SHORT_VIBRATE_ONCE = 1;
    public static final int EFFFCT_OTHER_STEPABLE_REGULATE = 152;
    private static final int ERTP_INDEX_NOTIFICATION_INBOUND = 253;
    private static final int RTP_INDEX_AFGAME_DOUBLE_KILL = 101;
    private static final int RTP_INDEX_AFGAME_NORMAL_KILL = 100;
    private static final int RTP_INDEX_AFGAME_PANTA_KILL = 104;
    private static final int RTP_INDEX_AFGAME_TRIBLE_KILL = 102;
    private static final int RTP_INDEX_AFGAME_ULTRA_KILL = 103;
    private static final int RTP_INDEX_ALARM_ALARM_CLOCK = 262;
    private static final int RTP_INDEX_ALARM_BEEP = 263;
    private static final int RTP_INDEX_ALARM_BREEZE = 264;
    private static final int RTP_INDEX_ALARM_CLOUDSCAPE = 94;
    private static final int RTP_INDEX_ALARM_DAWN = 265;
    private static final int RTP_INDEX_ALARM_DREAM = 266;
    private static final int RTP_INDEX_ALARM_FLUTTERING = 267;
    private static final int RTP_INDEX_ALARM_FLYER = 268;
    private static final int RTP_INDEX_ALARM_INTERESTING = 269;
    private static final int RTP_INDEX_ALARM_LEISURELY = 270;
    private static final int RTP_INDEX_ALARM_MEMORY = 271;
    private static final int RTP_INDEX_ALARM_PACMAN = 198;
    private static final int RTP_INDEX_ALARM_RELIEVED = 272;
    private static final int RTP_INDEX_ALARM_RIPPLE = 273;
    private static final int RTP_INDEX_ALARM_SLOWLY = 274;
    private static final int RTP_INDEX_ALARM_SPRING = 275;
    private static final int RTP_INDEX_ALARM_STARS = 276;
    private static final int RTP_INDEX_ALARM_SURGING = 277;
    private static final int RTP_INDEX_ALARM_TACTFULLY = 278;
    private static final int RTP_INDEX_ALARM_THE_WIND = 279;
    private static final int RTP_INDEX_ALARM_WALKING_IN_THE_RAIN = 280;
    private static final int RTP_INDEX_ALARM_WEATHER_CLOUDY = 143;
    private static final int RTP_INDEX_ALARM_WEATHER_DEFAULT = 145;
    private static final int RTP_INDEX_ALARM_WEATHER_RAIN = 149;
    private static final int RTP_INDEX_ALARM_WEATHER_SMOG = 147;
    private static final int RTP_INDEX_ALARM_WEATHER_SNOW = 148;
    private static final int RTP_INDEX_ALARM_WEATHER_SUNNY = 146;
    private static final int RTP_INDEX_ALARM_WEATHER_THUNDERSTORM = 144;
    private static final int RTP_INDEX_ALARM_WEATHER_WIND = 142;
    private static final int RTP_INDEX_ALERTSLIDER_DOWN = 308;
    private static final int RTP_INDEX_ARTIST_ALARM = 153;
    private static final int RTP_INDEX_ARTIST_NOTIFICATION = 150;
    private static final int RTP_INDEX_ARTIST_RINGTONE = 151;
    private static final int RTP_INDEX_ARTIST_TEXT = 152;
    private static final int RTP_INDEX_ATTACH_TO_MIDDLE = 54;
    private static final int RTP_INDEX_AUDITION = 301;
    private static final int RTP_INDEX_BREATHE_SPREAD_OUT = 55;
    private static final int RTP_INDEX_BREATH_SIMULATION = 118;
    private static final int RTP_INDEX_BULB = 186;
    private static final int RTP_INDEX_CANYON_CALL = 90;
    private static final int RTP_INDEX_CHARGING_SIMULATION = 108;
    private static final int RTP_INDEX_CHASE = 293;
    private static final int RTP_INDEX_CLIMBER = 292;
    private static final int RTP_INDEX_CLOSE = 175;
    private static final int RTP_INDEX_COMPATIBLE_1 = 179;
    private static final int RTP_INDEX_COMPATIBLE_2 = 180;
    private static final int RTP_INDEX_COMPLETE = 185;
    private static final int RTP_INDEX_EFFECT_ALARM_GOODENERGY = 95;
    private static final int RTP_INDEX_EFFECT_NOTIFICATION_BLINK = 96;
    private static final int RTP_INDEX_EFFECT_NOTIFICATION_WHOOPDOOP = 97;
    private static final int RTP_INDEX_EFFECT_RINGTONE_FEELING = 98;
    private static final int RTP_INDEX_EFFECT_RINGTONE_NATURE = 99;
    private static final int RTP_INDEX_ELASTICITY = 187;
    private static final int RTP_INDEX_EMULATION_KEYBOARD_DOWN = 302;
    private static final int RTP_INDEX_EMULATION_KEYBOARD_UP = 303;
    private static final int RTP_INDEX_ERROR_MESSAGE = 46;
    private static final int RTP_INDEX_FINGERPRINT_CORRECT_EFFECT = 109;
    private static final int RTP_INDEX_GAME_RINGTON = 297;
    private static final int RTP_INDEX_HALF_LAP = 176;
    private static final int RTP_INDEX_HEARTBEAT = 43;
    private static final int RTP_INDEX_IN_GAME_ALARM = 299;
    private static final int RTP_INDEX_IN_GAME_SMS = 300;
    private static final int RTP_INDEX_KEYBOARD_MEDIUM = 111;
    private static final int RTP_INDEX_KEYBOARD_STRONG = 112;
    private static final int RTP_INDEX_KEYBOARD_WEAK = 110;
    private static final int RTP_INDEX_LONG_VIBRATE = 56;
    private static final int RTP_INDEX_MYSTIC_STORE = 91;
    private static final int RTP_INDEX_NOTIFICATION_ALLAY = 237;
    private static final int RTP_INDEX_NOTIFICATION_ALLUSION = 238;
    private static final int RTP_INDEX_NOTIFICATION_AMIABLE = 239;
    private static final int RTP_INDEX_NOTIFICATION_BEATING = 89;
    private static final int RTP_INDEX_NOTIFICATION_BEATING_2 = 341;
    private static final int RTP_INDEX_NOTIFICATION_BLARE = 240;
    private static final int RTP_INDEX_NOTIFICATION_BLISSFUL = 241;
    private static final int RTP_INDEX_NOTIFICATION_BLOCK = 82;
    private static final int RTP_INDEX_NOTIFICATION_BLOCK_2 = 345;
    private static final int RTP_INDEX_NOTIFICATION_BOWLBELL = 88;
    private static final int RTP_INDEX_NOTIFICATION_BOWLBELL_2 = 337;
    private static final int RTP_INDEX_NOTIFICATION_BRISK = 242;
    private static final int RTP_INDEX_NOTIFICATION_BUBBLE = 243;
    private static final int RTP_INDEX_NOTIFICATION_CHEERFUL = 244;
    private static final int RTP_INDEX_NOTIFICATION_CIRCLE = 80;
    private static final int RTP_INDEX_NOTIFICATION_CLEAR = 245;
    private static final int RTP_INDEX_NOTIFICATION_COMELY = 246;
    private static final int RTP_INDEX_NOTIFICATION_COZY = 247;
    private static final int RTP_INDEX_NOTIFICATION_CRYSTALCLEAR = 128;
    private static final int RTP_INDEX_NOTIFICATION_CRYSTALCLEAR_2 = 322;
    private static final int RTP_INDEX_NOTIFICATION_DING = 248;
    private static final int RTP_INDEX_NOTIFICATION_EFFERVESCE = 249;
    private static final int RTP_INDEX_NOTIFICATION_ELEGANT = 250;
    private static final int RTP_INDEX_NOTIFICATION_EMERGE = 123;
    private static final int RTP_INDEX_NOTIFICATION_EMERGE_2 = 324;
    private static final int RTP_INDEX_NOTIFICATION_FREE = 251;
    private static final int RTP_INDEX_NOTIFICATION_FUN = 13;
    private static final int RTP_INDEX_NOTIFICATION_FUN_2 = 332;
    private static final int RTP_INDEX_NOTIFICATION_GRANULES = 9;
    private static final int RTP_INDEX_NOTIFICATION_GRANULES_2 = 336;
    private static final int RTP_INDEX_NOTIFICATION_HALLUCINATION = 252;
    private static final int RTP_INDEX_NOTIFICATION_HARP = 131;
    private static final int RTP_INDEX_NOTIFICATION_HARP_2 = 326;
    private static final int RTP_INDEX_NOTIFICATION_HEY = 83;
    private static final int RTP_INDEX_NOTIFICATION_HEY_2 = 339;
    private static final int RTP_INDEX_NOTIFICATION_INGENIOUS = 12;
    private static final int RTP_INDEX_NOTIFICATION_INGENIOUS_2 = 335;
    private static final int RTP_INDEX_NOTIFICATION_INSTANT = 2;
    private static final int RTP_INDEX_NOTIFICATION_INSTANT_2 = 331;
    private static final int RTP_INDEX_NOTIFICATION_JINGLE_2 = 320;
    private static final int RTP_INDEX_NOTIFICATION_JOY = 129;
    private static final int RTP_INDEX_NOTIFICATION_JOY_2 = 329;
    private static final int RTP_INDEX_NOTIFICATION_LIGHT = 254;
    private static final int RTP_INDEX_NOTIFICATION_MEET = 255;
    private static final int RTP_INDEX_NOTIFICATION_NAIVETY = 256;
    private static final int RTP_INDEX_NOTIFICATION_NEWS = 85;
    private static final int RTP_INDEX_NOTIFICATION_NEWS_2 = 340;
    private static final int RTP_INDEX_NOTIFICATION_ONEPLUS_TWINKLE = 260;
    private static final int RTP_INDEX_NOTIFICATION_OVERTONE = 132;
    private static final int RTP_INDEX_NOTIFICATION_OVERTONE_2 = 327;
    private static final int RTP_INDEX_NOTIFICATION_PACMAN = 199;
    private static final int RTP_INDEX_NOTIFICATION_PERCUSSION = 126;
    private static final int RTP_INDEX_NOTIFICATION_PERCUSSION_2 = 328;
    private static final int RTP_INDEX_NOTIFICATION_QUICKLY = 257;
    private static final int RTP_INDEX_NOTIFICATION_RECEIVE = 15;
    private static final int RTP_INDEX_NOTIFICATION_RECEIVE_2 = 333;
    private static final int RTP_INDEX_NOTIFICATION_RHYTHM = 258;
    private static final int RTP_INDEX_NOTIFICATION_RIPPLES = 127;
    private static final int RTP_INDEX_NOTIFICATION_RIPPLES_2 = 325;
    private static final int RTP_INDEX_NOTIFICATION_RISE = 81;
    private static final int RTP_INDEX_NOTIFICATION_RISE_2 = 338;
    private static final int RTP_INDEX_NOTIFICATION_ROUND_2 = 344;
    private static final int RTP_INDEX_NOTIFICATION_SHORT = 87;
    private static final int RTP_INDEX_NOTIFICATION_SHORT_2 = 342;
    private static final int RTP_INDEX_NOTIFICATION_SIMPLE = 133;
    private static final int RTP_INDEX_NOTIFICATION_SIMPLE_2 = 321;
    private static final int RTP_INDEX_NOTIFICATION_SPLASH = 16;
    private static final int RTP_INDEX_NOTIFICATION_SPLASH_2 = 334;
    private static final int RTP_INDEX_NOTIFICATION_SURPRISE = 259;
    private static final int RTP_INDEX_NOTIFICATION_TOP = 86;
    private static final int RTP_INDEX_NOTIFICATION_TOP_2 = 343;
    private static final int RTP_INDEX_NOTIFICATION_TUNES = 125;
    private static final int RTP_INDEX_NOTIFICATION_TUNES_2 = 323;
    private static final int RTP_INDEX_NOTIFICATION_TWINKLE = 130;
    private static final int RTP_INDEX_NOTIFICATION_TWINKLE_2 = 330;
    private static final int RTP_INDEX_NOTIFICATION_ZANZA = 84;
    private static final int RTP_INDEX_NOTIFICATION_ZANZA_2 = 346;
    private static final int RTP_INDEX_RAPID = 45;
    private static final int RTP_INDEX_RECENT_TASK_FEEDBACK = 309;
    private static final int RTP_INDEX_REMIND = 44;
    private static final int RTP_INDEX_RINGTONE_ALACRITY = 201;
    private static final int RTP_INDEX_RINGTONE_AMENITY = 202;
    private static final int RTP_INDEX_RINGTONE_BLISS = 138;
    private static final int RTP_INDEX_RINGTONE_BLUES = 203;
    private static final int RTP_INDEX_RINGTONE_BOUNCE = 204;
    private static final int RTP_INDEX_RINGTONE_CALM = 140;
    private static final int RTP_INDEX_RINGTONE_CHILDHOOD = 19;
    private static final int RTP_INDEX_RINGTONE_CLASSIC = 135;
    private static final int RTP_INDEX_RINGTONE_CLOUD = 206;
    private static final int RTP_INDEX_RINGTONE_COMMUTE = 20;
    private static final int RTP_INDEX_RINGTONE_CYCLOTRON = 207;
    private static final int RTP_INDEX_RINGTONE_DANCE = 30;
    private static final int RTP_INDEX_RINGTONE_DAZZLE = 28;
    private static final int RTP_INDEX_RINGTONE_DELIGHT = 141;
    private static final int RTP_INDEX_RINGTONE_DISTINCT = 208;
    private static final int RTP_INDEX_RINGTONE_DREAM = 139;
    private static final int RTP_INDEX_RINGTONE_DREAMJAZZ = 78;
    private static final int RTP_INDEX_RINGTONE_DYNAMIC = 209;
    private static final int RTP_INDEX_RINGTONE_EAGER = 232;
    private static final int RTP_INDEX_RINGTONE_EASTERN_TRANQUILITY = 71;
    private static final int RTP_INDEX_RINGTONE_EBULLITION = 233;
    private static final int RTP_INDEX_RINGTONE_ECHO = 210;
    private static final int RTP_INDEX_RINGTONE_ECLUB = 73;
    private static final int RTP_INDEX_RINGTONE_EXPECT = 211;
    private static final int RTP_INDEX_RINGTONE_FAIRVIEWS = 121;
    private static final int RTP_INDEX_RINGTONE_FANATICAL = 212;
    private static final int RTP_INDEX_RINGTONE_FIREFLY = 22;
    private static final int RTP_INDEX_RINGTONE_FRESHMORNING = 70;
    private static final int RTP_INDEX_RINGTONE_FRIENDSHIP = 234;
    private static final int RTP_INDEX_RINGTONE_FUNK = 75;
    private static final int RTP_INDEX_RINGTONE_FUNKY = 213;
    private static final int RTP_INDEX_RINGTONE_GAZINGOUT = 24;
    private static final int RTP_INDEX_RINGTONE_GUITAR = 214;
    private static final int RTP_INDEX_RINGTONE_HARPING = 215;
    private static final int RTP_INDEX_RINGTONE_HIGHLIGHT = 216;
    private static final int RTP_INDEX_RINGTONE_HOLIDAY = 74;
    private static final int RTP_INDEX_RINGTONE_HOUSEMUSIC = 76;
    private static final int RTP_INDEX_RINGTONE_INNOCENCE = 218;
    private static final int RTP_INDEX_RINGTONE_JAZZ_LIFE = 235;
    private static final int RTP_INDEX_RINGTONE_JOURNEY = 219;
    private static final int RTP_INDEX_RINGTONE_JOYOUS = 220;
    private static final int RTP_INDEX_RINGTONE_LAKESIDE = 25;
    private static final int RTP_INDEX_RINGTONE_LAZY = 221;
    private static final int RTP_INDEX_RINGTONE_LDYL = 217;
    private static final int RTP_INDEX_RINGTONE_LONGING = 124;
    private static final int RTP_INDEX_RINGTONE_MARIMBA = 222;
    private static final int RTP_INDEX_RINGTONE_MEMORIES = 27;
    private static final int RTP_INDEX_RINGTONE_MODERN = 79;
    private static final int RTP_INDEX_RINGTONE_MYSTICAL = 223;
    private static final int RTP_INDEX_RINGTONE_NIGHT = 29;
    private static final int RTP_INDEX_RINGTONE_NOSTALGIC = 134;
    private static final int RTP_INDEX_RINGTONE_OLD_TELEPHONE = 224;
    private static final int RTP_INDEX_RINGTONE_ONEPLUS_CALM = 205;
    private static final int RTP_INDEX_RINGTONE_ONEPLUS_TUNE = 225;
    private static final int RTP_INDEX_RINGTONE_OPTIMISTIC = 227;
    private static final int RTP_INDEX_RINGTONE_PACMAN = 200;
    private static final int RTP_INDEX_RINGTONE_PIANO = 228;
    private static final int RTP_INDEX_RINGTONE_PLAYPARK = 31;
    private static final int RTP_INDEX_RINGTONE_PURE = 49;
    private static final int RTP_INDEX_RINGTONE_RELAX = 32;
    private static final int RTP_INDEX_RINGTONE_RHYTHM = 226;
    private static final int RTP_INDEX_RINGTONE_ROMANCE = 137;
    private static final int RTP_INDEX_RINGTONE_SCHOOL = 17;
    private static final int RTP_INDEX_RINGTONE_SILENCE = 35;
    private static final int RTP_INDEX_RINGTONE_SOLITUDE = 34;
    private static final int RTP_INDEX_RINGTONE_STARS = 36;
    private static final int RTP_INDEX_RINGTONE_SUMMER = 37;
    private static final int RTP_INDEX_RINGTONE_SUN_GLITTERING = 236;
    private static final int RTP_INDEX_RINGTONE_TEMPLESOUND = 77;
    private static final int RTP_INDEX_RINGTONE_TRINKETS = 38;
    private static final int RTP_INDEX_RINGTONE_T_JINGLE = 231;
    private static final int RTP_INDEX_RINGTONE_VISIONS = 136;
    private static final int RTP_INDEX_RINGTONE_WATERCICADE = 72;
    private static final int RTP_INDEX_RINGTONE_WHIRL = 229;
    private static final int RTP_INDEX_RM_ITSRM = 161;
    private static final int RTP_INDEX_RM_JINGLE = 163;
    private static final int RTP_INDEX_RM_TUNE = 162;
    private static final int RTP_INDEX_ROCK = 296;
    private static final int RTP_INDEX_SGAME_DOUBLE_KILL = 61;
    private static final int RTP_INDEX_SGAME_FIRST_BLOOD = 60;
    private static final int RTP_INDEX_SGAME_GODLIKE = 68;
    private static final int RTP_INDEX_SGAME_KILLING_SPREE = 65;
    private static final int RTP_INDEX_SGAME_LEGENDARY = 69;
    private static final int RTP_INDEX_SGAME_PANTA_KILL = 64;
    private static final int RTP_INDEX_SGAME_RAMPAGE = 66;
    private static final int RTP_INDEX_SGAME_TRIBLE_KILL = 62;
    private static final int RTP_INDEX_SGAME_ULTRA_KILL = 63;
    private static final int RTP_INDEX_SGAME_UNSTOPPABLE = 67;
    private static final int RTP_INDEX_SPREAD_OUT = 47;
    private static final int RTP_INDEX_STEPABLE_REGULATE = 105;
    private static final int RTP_INDEX_STREAK = 42;
    private static final int RTP_INDEX_STRENGTH_LEVEL_BAR_EDGE = 107;
    private static final int RTP_INDEX_STRONG_ONE_SENCOND = 58;
    private static final int RTP_INDEX_STRONG_POINTFOUR_SENCOND = 57;
    private static final int RTP_INDEX_STYLESWITCH = 181;
    private static final int RTP_INDEX_STYLESWITCH_SOFT = 188;
    private static final int RTP_INDEX_SUSPENDBUTTON_BOTTOMOUT = 183;
    private static final int RTP_INDEX_SUSPENDBUTTON_MENU = 184;
    private static final int RTP_INDEX_SYMPHONIC = 41;
    private static final int RTP_INDEX_TELCEL_RIE = 119;
    private static final int RTP_INDEX_THREEFINGERS_LONG = 171;
    private static final int RTP_INDEX_THREEFINGERS_SCREENSHOT = 173;
    private static final int RTP_INDEX_THREEFINGERS_UP = 172;
    private static final int RTP_INDEX_TWOFINGERS_DOWN = 177;
    private static final int RTP_INDEX_TWOFINGERS_LONG = 178;
    private static final int RTP_INDEX_UNFOLD = 174;
    private static final int RTP_INDEX_VIBRATE_GT = 170;
    private static final int RTP_INDEX_VIRTUAL_KEY_FEEDBACK = 310;
    private static final int RTP_INDEX_VOICE_ASSISTANT = 122;
    private static final int RTP_INDEX_VOICE_LEVEL_BAR_EDGE = 106;
    private static final int RTP_INDEX_WAKE_UP_SAMURAI = 298;
    private static final int RTP_INDEX_WATERRIPPLE = 182;
    private static final int RTP_INDEX_WEAK_EMULATION_KEYBOARD_DOWN = 304;
    private static final int RTP_INDEX_WEAK_EMULATION_KEYBOARD_UP = 305;
    public static final int SETTINGS_VALUE_EFFECT_STRONG = 2400;
    public static final int STRENGTH_LIGHT = 0;
    public static final int STRENGTH_MEDIUM = 1;
    public static final int STRENGTH_STRONG = 2;
    private static final String TAG = "WaveformEffect";
    private static final int WAVEFORM_ID_ENROLL_FINGERPRINT_SUCCESS = 316;
    private static final int WAVEFORM_ID_LOCKSCREEN_FEEDBACK = 315;
    private static final int WAVEFORM_ID_MESSAGE_HEARTBEAT = 323;
    private static final int WAVEFORM_ID_MESSAGE_RAPID = 325;
    private static final int WAVEFORM_ID_MESSAGE_REMIND = 324;
    private static final int WAVEFORM_ID_MESSAGE_STREAK = 322;
    private static final int WAVEFORM_ID_MESSAGE_SYMPHONIC = 321;
    private static final int WAVEFORM_ID_SCREENSHOT_FEEDBACK = 318;
    private static final int WAVEFORM_ID_THREE_STAGE_KEY = 365;
    private static final int WAVEFORM_INDEX_BIG_SCALE = 13;
    public static final int WAVEFORM_INDEX_INVALID = -1;
    private static final int WAVEFORM_INDEX_MODERATE_SHORT = 3;
    private static final int WAVEFORM_INDEX_PUBG_RIFLE = 10;
    private static final int WAVEFORM_INDEX_PUBG_SHORT_GUN = 11;
    private static final int WAVEFORM_INDEX_PUBG_SNIPER = 12;
    private static final int WAVEFORM_INDEX_RAZER_CLICKY_PRESS = 8;
    private static final int WAVEFORM_INDEX_RAZER_LINEAR_PRESS = 9;
    private static final int WAVEFORM_INDEX_RUSH_LEFT_TO_RIGHT = 5;
    private static final int WAVEFORM_INDEX_SMALL_SCALE = 14;
    private static final int WAVEFORM_INDEX_STRONG_GRANULAR = 6;
    private static final int WAVEFORM_INDEX_THREE_DIMENSION_TOUCH = 4;
    private static final int WAVEFORM_INDEX_WEAKEST_SHORT = 1;
    private static final int WAVEFORM_INDEX_WEAK_GRANULAR = 7;
    private static final int WAVEFORM_INDEX_WEAK_SHORT = 2;
    public static final int WAVEFORM_NODE_INVALID = -1;
    public static final int WAVEFORM_NODE_RAM = 1;
    public static final int WAVEFORM_NODE_RTP = 2;
    private boolean mAsynchronous;
    private boolean mEffectLoop;
    private int mEffectStrength;
    private int mEffectType;
    private boolean mIsRingtoneCustomized;
    private String mRingtoneFilePath;
    private int mRingtoneVibrateType;
    private boolean mStrengthSettingEnabled;
    private int mUsageHint;
    private IVibratorManagerService mVibratorService;

    private WaveformEffect() {
        this.mEffectType = -1;
        this.mEffectStrength = -1;
        this.mEffectLoop = false;
        this.mRingtoneVibrateType = -1;
        this.mIsRingtoneCustomized = false;
        this.mRingtoneFilePath = "";
        this.mStrengthSettingEnabled = true;
        this.mAsynchronous = false;
        this.mUsageHint = 0;
    }

    private WaveformEffect(Parcel in) {
        this.mEffectType = -1;
        this.mEffectStrength = -1;
        this.mEffectLoop = false;
        this.mRingtoneVibrateType = -1;
        this.mIsRingtoneCustomized = false;
        this.mRingtoneFilePath = "";
        this.mStrengthSettingEnabled = true;
        this.mAsynchronous = false;
        this.mUsageHint = 0;
        this.mEffectType = in.readInt();
        this.mEffectStrength = in.readInt();
        this.mEffectLoop = in.readBoolean();
        this.mRingtoneVibrateType = in.readInt();
        this.mIsRingtoneCustomized = in.readBoolean();
        this.mRingtoneFilePath = in.readString();
        this.mStrengthSettingEnabled = in.readBoolean();
        this.mAsynchronous = in.readBoolean();
        this.mUsageHint = in.readInt();
    }

    @Deprecated
    public static boolean isRtpTouchWaveFormEffect(int type) {
        switch (type) {
            case 152:
            case 153:
            case 154:
            case 156:
            case 157:
            case 158:
            case 159:
            case 165:
            case 166:
            case 167:
            case 270:
            case 271:
            case 272:
            case 273:
            case 274:
            case 275:
            case 276:
            case 277:
            case 278:
            case 279:
            case 280:
            case EFFECT_OTHER_WATERRIPPLE /* 281 */:
            case EFFECT_OTHER_SUSPENDBUTTON_BOTTOMOUT /* 282 */:
            case EFFECT_OTHER_SUSPENDBUTTON_MENU /* 283 */:
            case EFFECT_OTHER_COMPLETE /* 284 */:
            case EFFECT_OTHER_BULB /* 285 */:
            case EFFECT_OTHER_ELASTICITY /* 286 */:
            case EFFECT_OTHER_STYLESWITCH_SOFT /* 287 */:
            case 302:
            case 303:
            case 304:
            case 305:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static boolean isRtpIndexTouchWaveFormEffect(int type) {
        switch (type) {
            case 105:
            case 107:
            case 110:
            case 111:
            case 112:
            case 302:
            case 303:
            case 304:
            case 305:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static String getRingtoneTitle(String path) {
        return null;
    }

    @Deprecated
    public static int getRingtoneWaveFormEffect(String title) {
        return -1;
    }

    @Deprecated
    public static int getWaveFormIndex(int effectId) {
        return -1;
    }

    @Deprecated
    public static long[] getWaveFormIndexDurationArray(int effectId) {
        return new long[]{-1};
    }

    public int getEffectType() {
        return this.mEffectType;
    }

    public int[] getWaveFormIndexArray() {
        return getWaveFormIndexArray(this.mEffectType);
    }

    public int[] getWaveFormIndexArray(int effectType) {
        int waveformIndex = -1;
        if (this.mVibratorService == null) {
            this.mVibratorService = IVibratorManagerService.Stub.asInterface(ServiceManager.getService("vibrator_manager"));
        }
        if (this.mVibratorService != null) {
            Slog.d(TAG, "access vibrator manager service failed");
        }
        return new int[]{waveformIndex};
    }

    public long[] getWaveFormDurationArray() {
        return getWaveFormDurationArray(this.mEffectType);
    }

    public long[] getWaveFormDurationArray(int effectType) {
        long duration = 0;
        if (this.mVibratorService == null) {
            this.mVibratorService = IVibratorManagerService.Stub.asInterface(ServiceManager.getService("vibrator_manager"));
        }
        if (this.mVibratorService != null) {
            Slog.d(TAG, "access linear motor vibrator service failed");
        }
        Slog.d(TAG, "getWaveFormDurationArray effectType=" + effectType + " duration=" + duration);
        return new long[]{duration};
    }

    public int getEffectStrength() {
        return this.mEffectStrength;
    }

    public boolean getEffectLoop() {
        return this.mEffectLoop;
    }

    public int getRingtoneVibrateType() {
        return this.mRingtoneVibrateType;
    }

    public boolean getIsRingtoneCustomized() {
        return this.mIsRingtoneCustomized;
    }

    public String getRingtoneFilePath() {
        return this.mRingtoneFilePath;
    }

    public boolean getStrengthSettingEnabled() {
        return this.mStrengthSettingEnabled;
    }

    public boolean getAsynchronous() {
        return this.mAsynchronous;
    }

    public int getUsageHint() {
        return this.mUsageHint;
    }

    public int getWaveFormNodeType() {
        if (this.mRingtoneVibrateType == -1) {
            if (this.mVibratorService == null) {
                this.mVibratorService = IVibratorManagerService.Stub.asInterface(ServiceManager.getService("vibrator_manager"));
            }
        }
        return -1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mEffectType);
        dest.writeInt(this.mEffectStrength);
        dest.writeBoolean(this.mEffectLoop);
        dest.writeInt(this.mRingtoneVibrateType);
        dest.writeBoolean(this.mIsRingtoneCustomized);
        dest.writeString(this.mRingtoneFilePath);
        dest.writeBoolean(this.mStrengthSettingEnabled);
        dest.writeBoolean(this.mAsynchronous);
        dest.writeInt(this.mUsageHint);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaveformEffect that = (WaveformEffect) o;
        if (this.mEffectType == that.getEffectType() && this.mEffectStrength == that.getEffectStrength() && this.mEffectLoop == that.getEffectLoop() && this.mRingtoneVibrateType == that.getRingtoneVibrateType() && this.mIsRingtoneCustomized == that.getIsRingtoneCustomized() && this.mRingtoneFilePath.equals(that.getRingtoneFilePath()) && this.mStrengthSettingEnabled == that.getStrengthSettingEnabled() && this.mAsynchronous == that.getAsynchronous() && this.mUsageHint == that.getUsageHint()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[0]);
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("WaveformEffect { ").append(Integer.toHexString(System.identityHashCode(this)));
        msg.append(", effectType=").append(this.mEffectType);
        msg.append(", effectStrength=").append(this.mEffectStrength);
        msg.append(", effectLoop=").append(this.mEffectLoop);
        msg.append(", ringtoneVibrateType=").append(this.mRingtoneVibrateType);
        msg.append(", isRingtoneCustomized=").append(this.mIsRingtoneCustomized);
        msg.append(", ringtoneFilePath=").append(this.mRingtoneFilePath);
        msg.append(", strengthSettingEnabled=").append(this.mStrengthSettingEnabled);
        msg.append(", asynchronous=").append(this.mAsynchronous);
        msg.append(", usageHint=").append(this.mUsageHint);
        msg.append(" }");
        return msg.toString();
    }

    public static class Builder {
        private boolean mAsynchronous;
        private boolean mEffectLoop;
        private int mEffectStrength;
        private int mEffectType;
        private boolean mIsRingtoneCustomized;
        private String mRingtoneFilePath;
        private int mRingtoneVibrateType;
        private boolean mStrengthSettingEnabled;
        private int mUsageHint;

        public Builder() {
            this.mEffectType = -1;
            this.mEffectStrength = -1;
            this.mEffectLoop = false;
            this.mRingtoneVibrateType = -1;
            this.mIsRingtoneCustomized = false;
            this.mRingtoneFilePath = "";
            this.mStrengthSettingEnabled = true;
            this.mAsynchronous = false;
            this.mUsageHint = 0;
        }

        public Builder(WaveformEffect we) {
            this.mEffectType = -1;
            this.mEffectStrength = -1;
            this.mEffectLoop = false;
            this.mRingtoneVibrateType = -1;
            this.mIsRingtoneCustomized = false;
            this.mRingtoneFilePath = "";
            this.mStrengthSettingEnabled = true;
            this.mAsynchronous = false;
            this.mUsageHint = 0;
            this.mEffectType = we.mEffectType;
        }

        public WaveformEffect build() {
            WaveformEffect we = new WaveformEffect();
            we.mEffectType = this.mEffectType;
            we.mEffectStrength = this.mEffectStrength;
            we.mEffectLoop = this.mEffectLoop;
            we.mRingtoneVibrateType = this.mRingtoneVibrateType;
            we.mIsRingtoneCustomized = this.mIsRingtoneCustomized;
            we.mRingtoneFilePath = this.mRingtoneFilePath;
            if (this.mEffectStrength == -1 && !this.mStrengthSettingEnabled) {
                Slog.d(WaveformEffect.TAG, "When the application side does not transmit the vibration strength,force the conversion of mStrengthSettingEnabled to true");
                this.mStrengthSettingEnabled = true;
            }
            we.mStrengthSettingEnabled = this.mStrengthSettingEnabled;
            we.mAsynchronous = this.mAsynchronous;
            we.mUsageHint = this.mUsageHint;
            return we;
        }

        public Builder setEffectType(int type) {
            this.mEffectType = type;
            return this;
        }

        public Builder setEffectStrength(int strength) {
            if (strength <= 2400) {
                this.mEffectStrength = strength;
            } else {
                this.mEffectStrength = -1;
            }
            return this;
        }

        public Builder setEffectLoop(boolean loop) {
            this.mEffectLoop = loop;
            return this;
        }

        public Builder setRingtoneVibrateType(int type) {
            this.mRingtoneVibrateType = type;
            return this;
        }

        public Builder setIsRingtoneCustomized(boolean customized) {
            this.mIsRingtoneCustomized = customized;
            return this;
        }

        public Builder setRingtoneFilePath(String path) {
            this.mRingtoneFilePath = path;
            return this;
        }

        public Builder setStrengthSettingEnabled(boolean enabled) {
            this.mStrengthSettingEnabled = enabled;
            return this;
        }

        public Builder setAsynchronous(boolean async) {
            this.mAsynchronous = async;
            return this;
        }

        public Builder setUsageHint(int usageHint) {
            this.mUsageHint = usageHint;
            return this;
        }
    }
}
