import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryTest : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("android-kotlin-multiplatform-library"))
                apply(libs.findPluginId("kotlin-multiplatform"))
                apply(libs.findPluginId("compose-compiler"))
                apply(libs.findPluginId("jetbrains-compose"))
            }
            kotlin1(extensions.getByType<KotlinMultiplatformExtension>())
//            android(extensions.getByType<LibraryExtension>())
//            dependencies {
//                add("debugImplementation", compose.uiTooling)
//            }
        }
    }
}
