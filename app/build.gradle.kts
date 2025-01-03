@file:OptIn(ExperimentalKotlinGradlePluginApi::class, ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.hulkdx.kmp.application)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        instrumentedTestVariant {
            sourceSetTree.set(KotlinSourceSetTree.test)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.libs.navigationDecompose)

            implementation(projects.feature.auth)
            implementation(projects.feature.home)
            implementation(projects.feature.developer)
            implementation(projects.feature.book)
            implementation(projects.feature.profile)
            implementation(projects.feature.review)
            implementation(projects.feature.pro.auth)
            implementation(projects.feature.pro.schedule)
            implementation(projects.feature.pro.availability)
            implementation(projects.feature.pro.profile)

            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.logging)
            implementation(libs.androidx.dataStore.core)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
        commonTest.dependencies {
            implementation(projects.libs.commonTests)
            implementation(kotlin("test"))
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
            implementation(libs.koin.test)
            implementation(compose.uiTest)
            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.ktor.mock)
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = namespace
        targetSdk = 34
        versionCode = 3
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".debug"
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    dependencies {
        implementation(libs.ktor.okhttp)
        implementation(libs.koin.android)
        implementation(libs.androidx.activity.compose)
        debugImplementation(libs.leakcanary)

        androidTestImplementation(libs.androidx.ui.test.junit4.android)
        debugImplementation(libs.androidx.ui.test.manifest)
    }
}
