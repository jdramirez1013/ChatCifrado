plugins {
    id("java-platform")
    id("maven-publish")
}

val core = "1.8.0"
val lifecycleRuntime = "2.3.1"
val activityCompose = "1.7.0-alpha02"
val material3 = "1.0.0-alpha02"
val junit = "4.13.2"
val testJunit = "1.1.3"
val testEspresso = "3.4.0"
val composeUiTest = "1.8.0"
val firebaseBom = "31.0.2"
val playServiceAuth = "20.3.0"


dependencies {
    constraints {
        api("${Libs.CORE_KTX}:${core}")
        api("${Libs.LIFECYCLE_RUNTIME}:${lifecycleRuntime}")
        api("${Libs.ACTIVITY_COMPOSE}:${activityCompose}")
        api("${Libs.COMPOSE_UI}:${Versions.COMPOSE}")
        api("${Libs.COMPOSE_TOOLING}:${Versions.COMPOSE}")
        api("${Libs.COMPOSE_TOOLING_PREVIEW}:${Versions.COMPOSE}")
        api("${Libs.MATERIAL3}:${material3}")
        api("${Libs.DAGGER_HILT}:${Versions.DAGGER_HILT}")
        api("${Libs.DAGGER_HILT_KAPT}:${Versions.DAGGER_HILT}")
        api("${Libs.FIREBASE_BOM}:${firebaseBom}")
        api(Libs.FIREBASE_AUTH)
        api("${Libs.PLAY_SERVICE_AUTH}:${playServiceAuth}")
        api(Libs.FIREBASE_FIRESTORE)
        api(Libs.FIREBASE_MESSAGING)

        api("${Libs.JUNIT}:${junit}")
        api("${Libs.TEST_JUNIT}:${testJunit}")
        api("${Libs.TEST_ESPRESSO}:${testEspresso}")
        api("${Libs.COMPOSE_TEST_JUNIT}:${Versions.COMPOSE}")
        api("${Libs.COMPOSE_TEST_MANIFEST}:${Versions.COMPOSE}")
    }
}