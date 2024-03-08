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
    implementation(project(":android:feature:home"))
    implementation(project(":android:feature:profile"))
    implementation(project(":android:feature:review"))
    implementation(project(":android:feature:book"))
    implementation(project(":android:core"))
    implementation(project(":android:app"))

    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation("androidx.compose.runtime:runtime")
    testImplementation("junit:junit:4.13.2")

    // For issues:
    // https://github.com/cashapp/paparazzi/issues/906 | https://github.com/cashapp/paparazzi/releases/tag/1.3.2
    // https://github.com/google/guava/issues/6801
    constraints {
        testImplementation("com.google.guava:guava") {
            attributes {
                attribute(
                    TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                    objects.named(TargetJvmEnvironment.STANDARD_JVM)
                )
            }
        }
    }
}

// Disable release build type
androidComponents {
    beforeVariants { variant ->
        variant.enable = variant.buildType == "debug"
    }
}
