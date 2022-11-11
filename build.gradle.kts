buildscript {
    dependencies {
        // ...
        classpath("com.google.gms:google-services:4.3.2")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.ANDROID apply false
    id("com.android.library") version Versions.ANDROID apply false
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
    id("org.jetbrains.kotlin.jvm") version Versions.KOTLIN apply false
    id("com.google.dagger.hilt.android") version Versions.DAGGER_HILT apply false

}