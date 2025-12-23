@file:OptIn(ExperimentalKotlinGradlePluginApi::class, ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.hulkdx.kmp.application)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets.all {
        // TODO: remove when https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-instant/ is not experimental
        languageSettings.optIn("kotlin.time.ExperimentalTime")
    }

    androidTarget {
        // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        instrumentedTestVariant {
            sourceSetTree.set(KotlinSourceSetTree.test)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export(projects.feature.bookingImpl)
        }
    }

    sourceSets {
        // NOTE: android dependencies are listed below
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.feature.authApi)
            implementation(projects.libs.navigationDecompose)

            implementation(projects.feature.authImpl)
            implementation(projects.feature.homeImpl)
            implementation(projects.feature.developerImpl)
            api(projects.feature.bookingImpl)
            implementation(projects.feature.profileImpl)
            implementation(projects.feature.reviewImpl)
            implementation(projects.feature.pro.authImpl)
            implementation(projects.feature.pro.scheduleImpl)
            implementation(projects.feature.pro.availabilityImpl)
            implementation(projects.feature.pro.profileImpl)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

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
