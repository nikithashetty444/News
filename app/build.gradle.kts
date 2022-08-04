import Dependencies.addAndroidTestLibs
import Dependencies.addAnnotationProcessor
import Dependencies.addAppLibs
import Dependencies.addTestLibs
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id( "kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        testInstrumentationRunner = Versions.androidJUnitTest

        buildConfigField("String", "API_KEY",  "\"pub_9735e38099bd96808e34c7c14b8b0c4686d2\"")

        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
                isDebuggable = true
            }
            getByName("release") {
                isMinifyEnabled = true
                isDebuggable = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
        composeOptions {
            kotlinCompilerExtensionVersion = Versions.compose
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = Versions.compose
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources {
            excludes += ("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    addAppLibs()
    addTestLibs()
    addAndroidTestLibs()
    addAnnotationProcessor()
}