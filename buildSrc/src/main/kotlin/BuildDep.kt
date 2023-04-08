/**
 * Dependencies that are used in the application
 *
 * https://developer.android.com/jetpack/androidx/versions
 */
object BuildDep {
    // --------------------------------------------------------------------------------
    // General
    // --------------------------------------------------------------------------------
    const val MIN_SDK_VERSION = 24
    const val COMPILE_SDK_VERSION = 33

    const val KOTLIN_VERSION = "1.8.10"

    // https://developer.android.com/studio/releases/gradle-plugin
    const val AGP_VERSION = "7.4.2"

    // --------------------------------------------------------------------------------
    // KMM
    // --------------------------------------------------------------------------------

    // https://github.com/icerockdev/moko-resources
    const val MOKO_RESOURCES_VERSION = "0.21.1"

    // https://developer.android.com/jetpack/androidx/releases/datastore
    const val DATASTORE_VERSION = "1.1.0-alpha04"

    // --------------------------------------------------------------------------------
    // Android Main
    // --------------------------------------------------------------------------------

    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_VERSION = "1.4.4"

    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_BOM = "2023.04.00"

    // https://github.com/Kotlin/kotlinx.coroutines
    const val COROUTINES_VERSION = "1.6.4"

    // https://github.com/InsertKoinIO/koin
    const val KOIN_VERSION = "3.4.0"

    // https://github.com/InsertKoinIO/koin
    const val KOIN_COMPOSE_VERSION = "3.4.3"

    // https://github.com/square/leakcanary
    const val LEAK_CANARY_VERSION = "2.10"

    // https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#ktor-dependencies
    const val KTOR_VERSION = "2.2.4"

    // https://github.com/google/accompanist
    const val ACCOMPANIST_VERSION = "0.30.1"

    // https://developer.android.com/jetpack/androidx/releases/lifecycle
    const val ANDROIDX_LIFECYCLE = "2.6.1"

    // --------------------------------------------------------------------------------
    // Android Test
    // --------------------------------------------------------------------------------

    // https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test
    const val COROUTINES_TEST_VERSION = COROUTINES_VERSION
}
