plugins {
    id("com.android.application").version(BuildDep.AGP_VERSION).apply(false)
    id("com.android.library").version(BuildDep.AGP_VERSION).apply(false)
    id("org.jetbrains.kotlin.android").version(BuildDep.KOTLIN_VERSION).apply(false)
    kotlin("multiplatform").version(BuildDep.KOTLIN_VERSION).apply(false)
    id("com.rickclephas.kmp.nativecoroutines") version BuildDep.IOS_KMP_NATIVE_VERSION apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
