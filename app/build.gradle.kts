plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.noter"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.noter"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        allWarningsAsErrors = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler.v2511)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.concurrent.futures.ktx)
    //view Model
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //navigation
    implementation(libs.androidx.navigation.compose)

}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}

// Add Hilt's Gradle plugin
apply(plugin = "dagger.hilt.android.plugin")


////Hilt
//implementation("com.google.dagger:hilt-android:2.51.1")
//kapt("com.google.dagger:hilt-android-compiler:2.51.1")
//
//
//val room_version = "2.6.1"
//
//implementation("androidx.room:room-runtime:$room_version")
//annotationProcessor("androidx.room:room-compiler:$room_version")
//implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
//
////view Model
//implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")
//
////navigation
//implementation("androidx.navigation:navigation-compose:2.7.7")
