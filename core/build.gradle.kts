plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.loki.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    //koin
    implementation(libs.koin.android)
    implementation(libs.koin.navigation)
    implementation(libs.koin.compose)
    testImplementation(libs.koin.test)

    //retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp3)
}