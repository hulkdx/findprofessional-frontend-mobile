import org.gradle.api.JavaVersion

/**
 * Dependencies that are used in the application
 *
 * https://developer.android.com/jetpack/androidx/versions
 */
object BuildDep {
    // --------------------------------------------------------------------------------
    // General
    // --------------------------------------------------------------------------------
    const val MIN_SDK_VERSION = 29
    const val COMPILE_SDK_VERSION = 34

    val JAVA_VERSION_SOURCE_COMPATIBILITY = JavaVersion.VERSION_17
    val JAVA_VERSION_TARGET_COMPATIBILITY = JAVA_VERSION_SOURCE_COMPATIBILITY

    const val KOTLIN_VERSION = "1.9.10"

    // https://developer.android.com/studio/releases/gradle-plugin
    const val AGP_VERSION = "8.1.2"

    // --------------------------------------------------------------------------------
    // KMM
    // --------------------------------------------------------------------------------

    // https://github.com/icerockdev/moko-resources
    const val MOKO_RESOURCES_VERSION = "0.23.0"

    // https://developer.android.com/jetpack/androidx/releases/datastore
    const val DATASTORE_VERSION = "1.1.0-alpha05"

    // --------------------------------------------------------------------------------
    // Android Main
    // --------------------------------------------------------------------------------

    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_VERSION = "1.5.3"

    // https://developer.android.com/jetpack/compose/bom/bom-mapping
    const val COMPOSE_BOM = "2023.10.00"

    // https://developer.android.com/jetpack/androidx/releases/navigation
    const val COMPOSE_NAVIGATION = "2.7.4"

    // https://github.com/Kotlin/kotlinx.coroutines
    const val COROUTINES_VERSION = "1.7.3"

    // https://github.com/InsertKoinIO/koin
    const val KOIN_VERSION = "3.5.0"

    // https://github.com/InsertKoinIO/koin
    const val KOIN_COMPOSE_VERSION = KOIN_VERSION

    // https://github.com/square/leakcanary
    const val LEAK_CANARY_VERSION = "2.12"

    // https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#ktor-dependencies
    const val KTOR_VERSION = "2.3.5"

    // https://developer.android.com/jetpack/androidx/releases/lifecycle
    const val ANDROIDX_ACTIVITY = "1.8.0"

    // https://developer.android.com/jetpack/androidx/releases/lifecycle
    const val ANDROIDX_LIFECYCLE = "2.6.2"

    // https://github.com/coil-kt/coil
    const val COIL_VERSION = "2.4.0"

    // --------------------------------------------------------------------------------
    // Android Test
    // --------------------------------------------------------------------------------

    // https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test
    const val COROUTINES_TEST_VERSION = COROUTINES_VERSION
}
