/**
 * Dependencies that are used in the application
 *
 * https://developer.android.com/jetpack/androidx/versions
 */
object BuildDep {
    const val MIN_SDK_VERSION = 24
    const val COMPILE_SDK_VERSION = 33

    const val KOTLIN_VERSION = "1.8.0"
    // https://developer.android.com/studio/releases/gradle-plugin
    const val AGP_VERSION = "7.4.0"

    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_VERSION = "1.4.0"
    // https://developer.android.com/jetpack/androidx/releases/compose
    const val COMPOSE_BOM = "2023.01.00"
    // https://developer.android.com/jetpack/androidx/releases/navigation
    private const val COMPOSE_NAVIGATION_VERSION = "2.5.3"
    // https://github.com/Kotlin/kotlinx.coroutines
    private const val COROUTINES_VERSION = "1.6.0"
    // https://github.com/InsertKoinIO/koin
    private const val KOIN_VERSION = "3.1.6"
    // https://github.com/rickclephas/KMP-NativeCoroutines
    const val IOS_KMP_NATIVE_VERSION = "0.11.4-new-mm"
    private const val LEAK_CANARY_VERSION = "2.8.1"

    // https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test
    private const val COROUTINES_TEST_VERSION = "1.5.2"
    // https://github.com/junit-team/junit5
    private const val JUNIT_VERSION = "5.7.2"
    // https://github.com/mockito/mockito
    private const val MOCKITO_VERSION = "3.10.0"
    private const val MOCKITO_JUNIT5_VERSION = "2.23.0"
    private const val EXT_JUNIT_TEST_VERSION = "1.1.3"

    // main dependencies
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION"
    const val COMPOSE_MATERIAL = "androidx.compose.material3:material3:1.0.1"
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:$COMPOSE_NAVIGATION_VERSION"

    const val KOIN_CORE = "io.insert-koin:koin-core:${KOIN_VERSION}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${KOIN_VERSION}"
    const val KOIN_COMPOSE = "io.insert-koin:koin-androidx-compose:${KOIN_VERSION}"

    // debug dependencies
    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:$LEAK_CANARY_VERSION"

    // test dependencies
    const val JUNIT_API = "org.junit.jupiter:junit-jupiter-api:$JUNIT_VERSION"
    const val JUNIT_ENGINE = "org.junit.jupiter:junit-jupiter-engine:$JUNIT_VERSION"
    const val JUNIT_PARAM = "org.junit.jupiter:junit-jupiter-params:$JUNIT_VERSION"
    const val MOCKITO = "org.mockito:mockito-core:$MOCKITO_VERSION"
    const val MOCKITO_JUNIT5 = "org.mockito:mockito-junit-jupiter:$MOCKITO_JUNIT5_VERSION"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_TEST_VERSION"

    // ui test dependencies
    const val COMPOSE_TEST = "androidx.compose.ui:ui-test-junit4:$COMPOSE_VERSION"
    const val EXT_JUNIT_TEST = "androidx.test.ext:junit:$EXT_JUNIT_TEST_VERSION"
}
