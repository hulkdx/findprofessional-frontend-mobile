plugins {
    alias(libs.plugins.hulkdx.android.library.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hulkdx.findprofessional.feature.profile"
}

dependencies {
    implementation(project(":android:core"))
    implementation(project(":android:feature:navigation"))
}
