import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

fun android(ext: CommonExtension<*, *, *, *, *, *>) = with(ext) {
    compileSdk = 34

    defaultConfig {
        minSdk = 29
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}
