import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.findPluginId(alias: String): String =
    findPlugin(alias).get().get().pluginId

internal val Project.compose
    get() = org.jetbrains.compose.ComposePlugin.Dependencies(this)
