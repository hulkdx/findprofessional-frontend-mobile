import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    alias(libs.plugins.hulkdx.android.library.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hulkdx.findprofessional.feature.home"
}

dependencies {
    implementation(project(":android:core"))
    implementation(project(":android:feature:navigation"))

    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.junit5.jupiter)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
    }
}
