plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.loki.data"
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


    //retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp3)

    //koin
    implementation(libs.koin.android)
    implementation(libs.koin.navigation)
    implementation(libs.koin.compose)
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":core")))
    testImplementation(libs.koin.test)

    //coroutines
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)
}