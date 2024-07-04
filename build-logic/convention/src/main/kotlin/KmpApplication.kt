import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpApplication : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("android-application"))
                apply(libs.findPluginId("kotlin-multiplatform"))
                apply(libs.findPluginId("compose-compiler"))
                apply(libs.findPluginId("jetbrains-compose"))
            }
            kotlin(extensions.getByType<KotlinMultiplatformExtension>())
            android(extensions.getByType<ApplicationExtension>())
        }
    }
}
