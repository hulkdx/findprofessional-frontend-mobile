plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.hulkdx.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.moko)
    alias(libs.plugins.jetbrains.compose)

    id(libs.plugins.kotlin.parcelize.get().pluginId)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            export(libs.moko.resources)
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                api(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                api(libs.moko.resources)
                implementation(libs.androidx.dataStore.core)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.core)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.logging)
                api(libs.kotlinx.datetime)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
                api(libs.moko.resources.compose)
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
                implementation(libs.ktor.darwin)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.koin.test)
            }
        }
    }
}

android {
    namespace = "com.hulkdx.findprofessional.common"

    // TODO: moko-resource temporary workaround:
    // https://github.com/icerockdev/moko-resources/issues/510
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
    sourceSets["main"].java.srcDirs("build/generated/moko/androidMain/src")

    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}

multiplatformResources {
    multiplatformResourcesPackage = "com.hulkdx.findprofessional.resources"
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.hulkdx.findprofessional.common.resources"
}
