object Dependencies {
    const val GRADLE = "com.android.tools.build:gradle:4.1.0"

    object Versions {
        const val ROOM = "2.2.6"
        const val NAVIGATION = "2.3.2"

        object Ktlint {
            const val CORE = "0.33.0"
            const val GRADLE = "9.4.1"
        }

        object Test {
            const val CORE = "1.2.0"
            const val JUNIT = "4.12"
            const val JUNIT_EXT = "1.1.1"
            const val ESPRESSO = "3.2.0"
        }

        object AndroidX {
            const val APP_COMPAT = "1.2.0"
            const val CORE_KTX = "1.3.2"
            const val FRAGMENT_KTX = "1.2.5"
            const val ACTIVITY_KTX = "1.1.0"
            const val MATERIAL = "1.2.0"
            const val KTX = "2.2.0"
            const val CONSTRAINT_LAYOUT = "2.0.2"
            const val VIEW_PAGER = "1.0.0"
        }

        object Kotlin {
            const val CORE = "1.4.21"
            const val COROUTINES = "1.4.2"
        }

        object Hilt {
            const val CORE = "2.31-alpha"
            const val ANDROID_X_COMPILER = "1.0.0-alpha02"
        }

        object Network {
            const val RETROFIT = "2.9.0"
            const val OKHTTP = "4.9.0"
        }

        object Utils {
            const val COIL = "1.0.0"
            const val PAGE_INDICATOR = "1.0.3@aar"
            const val TIMBER = "4.7.0"
            const val ALERTER = "6.2.1"
            const val SPINKIT = "1.4.0"
        }
    }

    object ProjectConstants {
        object ApplicationId {
            const val BASE = "com.skilos"
        }

        const val MINIMUM_SDK = 21
        const val COMPILE_SDK = 30
        const val TARGET_SDK = 30
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0.0"
        const val BUILD_TOOLS_VERSION = "30.0.3"
        const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
        const val proguardConsumerRules = "consumer-rules.pro"
    }

    object Kotlin {
        const val COROUTINE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINES}"
        const val COROUTINE_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}"
    }

    object Ktlint {
        const val CLASSPATH = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.Ktlint.GRADLE}"
        const val PLUGIN_NAME = "org.jlleitschuh.gradle.ktlint"
        const val GIT_HOOK = "plugins.git-hook"
    }

    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APP_COMPAT}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.AndroidX.FRAGMENT_KTX}"
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.AndroidX.ACTIVITY_KTX}"
        const val MATERIAL = "com.google.android.material:material:${Versions.AndroidX.MATERIAL}"
        const val VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.KTX}"
        const val LIVEDATA_KTX =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.KTX}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.CONSTRAINT_LAYOUT}"
        const val VIEW_PAGER = "androidx.viewpager2:viewpager2:${Versions.AndroidX.VIEW_PAGER}"
    }

    object Test {
        const val CORE = "androidx.test:core:${Versions.Test.CORE}"
        const val RULES = "androidx.test:rules:${Versions.Test.CORE}"
        const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.Test.JUNIT_EXT}"
    }

    object Navigation {
        const val UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
        const val RUNTIME = "androidx.navigation:navigation-runtime-ktx:${Versions.NAVIGATION}"
        const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val TESTING = "androidx.navigation:navigation-testing:${Versions.NAVIGATION}"
        const val CLASSPATH =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"
    }

    object Hilt {
        const val CORE = "com.google.dagger:hilt-core:${Versions.Hilt.CORE}"
        const val ANDROID = "com.google.dagger:hilt-android:${Versions.Hilt.CORE}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${Versions.Hilt.CORE}"
        const val ANDROID_X_COMPILER =
            "androidx.hilt:hilt-compiler:${Versions.Hilt.ANDROID_X_COMPILER}"
        const val VIEWMODEL =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Hilt.ANDROID_X_COMPILER}"
        const val TEST = "com.google.dagger:hilt-android-testing:${Versions.Hilt.CORE}"
        const val CLASSPATH = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.CORE}"
    }

    object Room {
        const val KTX = "androidx.room:room-ktx:${Versions.ROOM}"
        const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
        const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
        const val TEST = "androidx.room:room-testing:${Versions.ROOM}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.Network.OKHTTP}"
        const val LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OKHTTP}"
        const val GSON_CONVERTER =
            "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT}"
        const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.Network.OKHTTP}"
    }

    object Util {
        const val COIL = "io.coil-kt:coil:${Versions.Utils.COIL}"
        const val PAGER_INDICATOR =
            "com.romandanylyk:pageindicatorview:${Versions.Utils.PAGE_INDICATOR}"
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.Utils.TIMBER}"
        const val ALERTER = "com.tapadoo.android:alerter:${Versions.Utils.ALERTER}"
        const val SPINKIT = "com.github.ybq:Android-SpinKit:${Versions.Utils.SPINKIT}"
    }
}