plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.jelenai.myloyaltyapp.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerVersion = "1.6.21"
        kotlinCompilerExtensionVersion = "1.2.0-rc01"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    val coroutinesVersion = properties["version.kotlinx.coroutines"]
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.activity:activity-compose:1.6.0-alpha05")

    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.1.1")

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.1.1")

    // Material Design
    implementation("androidx.compose.material:material:1.1.1")

    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.1.1")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")

    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.1.1")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.4.2")

    // QR code generation
    implementation("androidmads.library.qrgenearator:QRGenearator:1.0.4")
    implementation("com.google.zxing:core:3.4.0")

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.37")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
}