/**
 * Dependencies that are used in the application
 *
 * https://developer.android.com/jetpack/androidx/versions
 */
object Dependencies {
    const val MIN_SDK_VERSION = 24
    const val COMPILE_SDK_VERSION = 30
    const val BUILD_TOOLS_VERSION = "30.0.3"

    private const val KOTLIN_VERSION = "1.4.32"
    private const val COMPOSE_VERSION = "1.0.0-beta07"
    private const val COMPOSE_ACTIVITY_VERSION = "1.3.0-alpha08"
    private const val BUILD_GRADLE_PLUGIN_VERSION = "7.0.0-beta01"

    private const val CORE_KTX_VERSION = "1.3.2"
    private const val RETROFIT_VERSION = "2.6.2"
    private const val COROUTINES_VERSION = "1.4.2"

    private const val JUNIT_VERSION = "5.7.1"
    private const val MOCKITO_VERSION = "2.23.0"

    // plugins
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KOTLIN_VERSION}"
    const val BUILD_GRADLE_PLUGIN = "com.android.tools.build:gradle:${BUILD_GRADLE_PLUGIN_VERSION}"

    // main dependencies
    const val CORE_KTX = "androidx.core:core-ktx:${CORE_KTX_VERSION}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"
    const val COMPOSE_UI = "androidx.compose.ui:ui:$COMPOSE_VERSION"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:$COMPOSE_VERSION"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:$COMPOSE_ACTIVITY_VERSION"

    // test dependencies
    const val JUNIT_API = "org.junit.jupiter:junit-jupiter-api:$JUNIT_VERSION"
    const val JUNIT_ENGINE = "org.junit.jupiter:junit-jupiter-engine:$JUNIT_VERSION"
    const val JUNIT_PARAM = "org.junit.jupiter:junit-jupiter-params:$JUNIT_VERSION"
    const val MOCKITO = "org.mockito:mockito-core:$MOCKITO_VERSION"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_VERSION"

    // ui test dependencies
    const val COMPOSE_TEST = "androidx.compose.ui:ui-test-junit4:$COMPOSE_VERSION"
}
