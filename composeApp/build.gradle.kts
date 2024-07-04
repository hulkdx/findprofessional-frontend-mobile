plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.hulkdx.android.application.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.jetbrains.compose)

}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
        }

        iosMain.dependencies {
        }

        commonTest.dependencies {
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.app"

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
        implementation(libs.androidx.activity.compose)
        implementation(libs.ktor.okhttp)

        debugImplementation(libs.leakcanary)
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.hulkdx.findprofessional.common.resources"
}
