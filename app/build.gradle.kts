plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
    id("kotlin-android-extensions")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = AppComp.APP_ID
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = AppComp.APP_ID
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName

        testInstrumentationRunner = AppComp.TEST_RUNNER_INST
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))

    implementation(Libs.CORE_KTX)
    implementation(Libs.LIFECYCLE_RUNTIME)
    implementation(Libs.ACTIVITY_COMPOSE)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_TOOLING_PREVIEW)
    implementation(Libs.MATERIAL3)
    implementation(Libs.DAGGER_HILT)

    implementation(platform(Libs.FIREBASE_BOM))
    implementation(Libs.FIREBASE_AUTH)
    implementation(Libs.PLAY_SERVICE_AUTH)
    implementation(Libs.FIREBASE_FIRESTORE)
    implementation(Libs.FIREBASE_MESSAGING)

    kapt(Libs.DAGGER_HILT_KAPT)

    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.TEST_JUNIT)
    androidTestImplementation(Libs.TEST_ESPRESSO)
    androidTestImplementation(Libs.COMPOSE_TEST_JUNIT)

    debugImplementation(Libs.COMPOSE_TOOLING)
    debugImplementation(Libs.COMPOSE_TEST_MANIFEST)
}

kapt {
    correctErrorTypes = true
}