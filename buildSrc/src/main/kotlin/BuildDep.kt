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
    const val MOKO_RESOURCES_VERSION = "0.20.1"
    const val DATASTORE_VERSION = "1.1.0-dev01"

    // --------------------------------------------------------------------------------
    // Android Main
    // --------------------------------------------------------------------------------

    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_VERSION = "1.4.3"

    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_BOM = "2023.01.00"

    // https://github.com/Kotlin/kotlinx.coroutines
    const val COROUTINES_VERSION = "1.6.4"

    // https://github.com/InsertKoinIO/koin
    const val KOIN_VERSION = "3.4.0"

    // https://github.com/InsertKoinIO/koin
    const val KOIN_COMPOSE_VERSION = "3.4.1"

    // https://github.com/square/leakcanary
    const val LEAK_CANARY_VERSION = "2.10"

    // https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#ktor-dependencies
    const val KTOR_VERSION = "2.2.2"

    // https://github.com/google/accompanist
    const val ACCOMPANIST_VERSION = "0.28.0"

    // https://developer.android.com/jetpack/androidx/releases/lifecycle
    const val ANDROIDX_LIFECYCLE = "2.6.0-rc01"

    // --------------------------------------------------------------------------------
    // Android Test
    // --------------------------------------------------------------------------------

    // https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test
    const val COROUTINES_TEST_VERSION = "1.6.4"
}