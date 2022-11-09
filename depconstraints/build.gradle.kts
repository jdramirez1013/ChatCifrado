plugins {
    id("java-platform")
    id("maven-publish")
}

val core = "1.8.0"
val lifecycle_runtime = "2.3.1"
val activity_compose = "1.7.0-alpha02"
val material3 = "1.0.0-alpha02"
val junit = "4.13.2"
val test_junit = "1.1.3"
val test_espresso = "3.4.0"
val compose_ui_test = "1.8.0"


dependencies {
    constraints {
        api("${Libs.CORE_KTX}:${core}")
        api("${Libs.LIFECYCLE_RUNTIME}:${lifecycle_runtime}")
        api("${Libs.ACTIVITY_COMPOSE}:${activity_compose}")
        api("${Libs.COMPOSE_UI}:${Versions.COMPOSE}")
        api("${Libs.COMPOSE_TOOLING}:${Versions.COMPOSE}")
        api("${Libs.COMPOSE_TOOLING_PREVIEW}:${Versions.COMPOSE}")
        api("${Libs.MATERIAL3}:${material3}")
        api("${Libs.DAGGER_HILT}:${Versions.DAGGER_HILT}")
        api("${Libs.DAGGER_HILT_KAPT}:${Versions.DAGGER_HILT}")
        api("${Libs.JUNIT}:${junit}")
        api("${Libs.TEST_JUNIT}:${test_junit}")
        api("${Libs.TEST_ESPRESSO}:${test_espresso}")
        api("${Libs.COMPOSE_TEST_JUNIT}:${Versions.COMPOSE}")
        api("${Libs.COMPOSE_TEST_MANIFEST}:${Versions.COMPOSE}")
    }
}