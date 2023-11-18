plugins {
    alias(libs.plugins.hulkdx.android.library.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.hulkdx.findprofessional.paparazzi.tests"
}

dependencies {
    implementation(project(":android:feature:authentication"))
    implementation(project(":android:core"))

    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation("androidx.compose.runtime:runtime")
    testImplementation("junit:junit:4.13.2")
}
