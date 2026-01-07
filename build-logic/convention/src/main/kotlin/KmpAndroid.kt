import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

const val COMPILE_SDK = 36
const val MIN_SDK = 30

fun android(ext: CommonExtension<*, *, *, *, *, *>) = with(ext) {
    compileSdk = COMPILE_SDK

    defaultConfig {
        minSdk = MIN_SDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}
