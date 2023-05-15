buildscript {
    dependencies {
        classpath(libs.android.gradlePlugin)
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}