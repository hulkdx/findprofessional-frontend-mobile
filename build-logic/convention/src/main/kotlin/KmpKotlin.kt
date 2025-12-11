import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.kotlin(ext: KotlinMultiplatformExtension) = with(ext) {
    androidTarget()

    sourceSets.all {
        // TODO: remove when https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-instant/ is not experimental
        languageSettings.optIn("kotlin.time.ExperimentalTime")
    }

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
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.ui)
        implementation(compose.components.resources)
        implementation(compose.components.uiToolingPreview)
    }
    sourceSets.androidMain.dependencies {
    }
    sourceSets.iosMain.dependencies {
    }
}
