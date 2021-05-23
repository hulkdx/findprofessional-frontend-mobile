plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Dependencies.COMPILE_SDK_VERSION
    buildToolsVersion = Dependencies.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = "com.hulkdx.findprofessional"
        minSdk = Dependencies.MIN_SDK_VERSION
        targetSdk = Dependencies.COMPILE_SDK_VERSION
        versionCode = 3
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }

    buildFeatures {
        compose = true
    }

    packagingOptions {
        // Required for Dependencies.COMPOSE_TEST
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":feature:authentication"))

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_UI_TOOLING)
    implementation(Dependencies.COMPOSE_ACTIVITY)

    androidTestImplementation(Dependencies.COMPOSE_TEST)

}
