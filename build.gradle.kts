import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

// TODO: remove when resource api is not experiment anymore
allprojects {
    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-opt-in=org.jetbrains.compose.resources.ExperimentalResourceApi",
            )
        }
    }
}
