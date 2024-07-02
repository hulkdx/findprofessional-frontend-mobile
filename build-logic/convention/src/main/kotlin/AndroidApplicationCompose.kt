import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationCompose : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply(libs.findPluginId("compose-compiler"))
                apply(libs.findPluginId("jetbrains-compose"))
            }

            configureAndroidCompose(extensions.getByType<ApplicationExtension>())
        }
    }
}
