import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.kotlin(ext: KotlinMultiplatformExtension) = with(ext) {
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

    sourceSets.commonMain.dependencies {
        implementation(composeLibs.runtime)
        implementation(composeLibs.material3)
    }
    sourceSets.androidMain.dependencies {
    }
    sourceSets.iosMain.dependencies {
    }
}
