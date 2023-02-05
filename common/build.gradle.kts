plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version BuildDep.KOTLIN_VERSION
    id("com.android.library")

    id("com.rickclephas.kmp.nativecoroutines") version BuildDep.IOS_KMP_NATIVE_VERSION
    id("dev.icerock.mobile.multiplatform-resources") version BuildDep.MOKO_RESOURCES_VERSION
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:${BuildDep.KOIN_VERSION}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDep.COROUTINES_VERSION}")
                implementation("io.ktor:ktor-client-core:${BuildDep.KTOR_VERSION}")
                implementation("io.ktor:ktor-client-content-negotiation:${BuildDep.KTOR_VERSION}")
                implementation("io.ktor:ktor-serialization-kotlinx-json:${BuildDep.KTOR_VERSION}")
                implementation("dev.icerock.moko:resources:${BuildDep.MOKO_RESOURCES_VERSION}")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:${BuildDep.KTOR_VERSION}")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:${BuildDep.KTOR_VERSION}")
            }
        }
    }
}

android {
    compileSdk = BuildDep.COMPILE_SDK_VERSION
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
    defaultConfig {
        minSdk = BuildDep.MIN_SDK_VERSION
        targetSdk = BuildDep.COMPILE_SDK_VERSION
    }
    namespace = "com.hulkdx.findprofessional.common"
}

multiplatformResources {
    multiplatformResourcesPackage = "com.hulkdx.findprofessional.resources"
}
