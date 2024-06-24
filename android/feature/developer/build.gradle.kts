plugins {
    alias(libs.plugins.hulkdx.android.library.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.hulkdx.findprofessional.feature.developer"
}

dependencies {
    implementation(project(":android:feature:navigation"))

    implementation(libs.androidx.activity.compose)
}
