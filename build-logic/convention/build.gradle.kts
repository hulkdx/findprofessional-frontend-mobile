plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "com.hulkdx.findprofessional.application.compose"
            implementationClass = "AndroidApplicationCompose"
        }
        register("androidLibraryCompose") {
            id = "com.hulkdx.findprofessional.library.compose"
            implementationClass = "AndroidLibraryCompose"
        }
    }
}
