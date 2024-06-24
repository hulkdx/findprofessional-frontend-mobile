import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

allprojects {
    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            freeCompilerArgs = listOf(
                // TODO: remove when resource api is not experiment anymore
                "-opt-in=org.jetbrains.compose.resources.ExperimentalResourceApi",
                // TODO: common parcelize, its probably fixed in the next kotlin version:
                //      https://issuetracker.google.com/issues/315775835
                "-P",
                "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=com.hulkdx.findprofessional.common.utils.CommonParcelize",
            )
        }
    }
}
