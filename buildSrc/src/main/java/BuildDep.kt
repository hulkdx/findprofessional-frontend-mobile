/**
 * Dependencies that are used in the application
 *
 * https://developer.android.com/jetpack/androidx/versions
 */
object BuildDep {
    const val MIN_SDK_VERSION = 24
    const val COMPILE_SDK_VERSION = 33

    // https://github.com/rickclephas/KMP-NativeCoroutines
    // NOTE:
    // KOTLIN_VERSION     depends on this
    // COROUTINES_VERSION depends on this
    const val IOS_KMP_NATIVE_VERSION = "0.13.2-kotlin-1.8.0-RC"
    // https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#ktor-dependencies
    const val KTOR_VERSION = "2.2.2"

    const val KOTLIN_VERSION = "1.8.0"
    // https://developer.android.com/studio/releases/gradle-plugin
    const val AGP_VERSION = "7.4.0"

    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_VERSION = "1.4.0"
    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_BOM = "2023.01.00"
    // https://developer.android.com/jetpack/androidx/releases/compose-material3
    const val COMPOSE_MATERIAL3_VERSION = "1.0.1"
    // https://developer.android.com/jetpack/androidx/releases/navigation
    const val COMPOSE_NAVIGATION_VERSION = "2.5.3"
    // https://github.com/Kotlin/kotlinx.coroutines
    const val COROUTINES_VERSION = "1.6.4"
    // https://github.com/InsertKoinIO/koin
    const val KOIN_VERSION = "3.3.2"
    // https://github.com/InsertKoinIO/koin
    const val KOIN_COMPOSE_VERSION = "3.4.1"
    // https://github.com/square/leakcanary
    const val LEAK_CANARY_VERSION = "2.8.1"

    // https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test
    const val COROUTINES_TEST_VERSION = "1.5.2"
    // https://github.com/junit-team/junit5
    const val JUNIT_VERSION = "5.7.2"
    // https://github.com/mockito/mockito
    const val MOCKITO_VERSION = "3.10.0"
    const val MOCKITO_JUNIT5_VERSION = "2.23.0"
}
