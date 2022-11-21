plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.jdr.data"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = AppComp.TEST_RUNNER_INST
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(platform(project(":depconstraints")))
    api(platform(project(":domain")))
    api(platform(project(":model")))
    kapt(platform(project(":depconstraints")))

    implementation(Libs.CORE_KTX)

    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.TEST_JUNIT)
    androidTestImplementation(Libs.TEST_ESPRESSO)
}