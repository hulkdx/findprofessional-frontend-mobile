plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kmpApplication") {
            id = "com.hulkdx.findprofessional.kmp.application"
            implementationClass = "KmpApplication"
        }
        register("kmpLibrary") {
            id = "com.hulkdx.findprofessional.kmp.library"
            implementationClass = "KmpLibrary"
        }
    }
}
