plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(Dependencies.ProjectConstants.COMPILE_SDK)
    buildToolsVersion(Dependencies.ProjectConstants.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Dependencies.ProjectConstants.MINIMUM_SDK)
        targetSdkVersion(Dependencies.ProjectConstants.TARGET_SDK)
        applicationId = Dependencies.ProjectConstants.ApplicationId.BASE
        versionCode = Dependencies.ProjectConstants.VERSION_CODE
        versionName = Dependencies.ProjectConstants.VERSION_NAME
        multiDexEnabled = true
        testInstrumentationRunner = Dependencies.ProjectConstants.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = true
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
        buildConfigField("String", "BASE_URL", getProperty("BASE_URL"))
        lintOptions { isAbortOnError = false }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        create("uat")
        named("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures.viewBinding = true

    sourceSets {
        val mainSrcSet = project.file("src/main/kotlin")
        findByName("main")?.java?.srcDirs(mainSrcSet)
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // Coroutines
    implementation(Dependencies.Kotlin.COROUTINE)
    implementation(Dependencies.Kotlin.COROUTINE_ANDROID)
    // AndroidX
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.FRAGMENT_KTX)
    implementation(Dependencies.AndroidX.MATERIAL)
    implementation(Dependencies.AndroidX.ACTIVITY_KTX)
    implementation(Dependencies.AndroidX.VIEWMODEL_KTX)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
    // Navigation
    implementation(Dependencies.Navigation.RUNTIME)
    implementation(Dependencies.Navigation.FRAGMENT)
    implementation(Dependencies.Navigation.UI)
    // Room
    implementation(Dependencies.Room.RUNTIME)
    implementation(Dependencies.Room.KTX)
    implementation(Dependencies.Room.COMPILER)
    // Hilt
    implementation(Dependencies.Hilt.CORE)
    implementation(Dependencies.Hilt.ANDROID)
    implementation(Dependencies.Hilt.VIEWMODEL)
    kapt(Dependencies.Hilt.COMPILER)
    kapt(Dependencies.Hilt.ANDROID_X_COMPILER)
    // Utils
    implementation(Dependencies.Util.TIMBER)
    implementation(Dependencies.Util.COIL)
    // Remote
    implementation(Dependencies.Network.OKHTTP)
    implementation(Dependencies.Network.RETROFIT)
    implementation(Dependencies.Network.GSON_CONVERTER)
    implementation(Dependencies.Network.LOGGING_INTERCEPTOR)
    testImplementation(Dependencies.Network.MOCK_WEB_SERVER)
    // Local
    implementation(Dependencies.Room.RUNTIME)
    implementation(Dependencies.Room.KTX)
    kapt(Dependencies.Room.COMPILER)
}

fun getProperty(key: String): String {
    return try {
        val properties = org.jetbrains.kotlin.konan.properties.loadProperties("local.properties")
        properties.getProperty(key)
    } catch (exception: Exception) {
        getSystemProperty(key)
    }
}

fun getSystemProperty(key: String): String = System.getenv(key)