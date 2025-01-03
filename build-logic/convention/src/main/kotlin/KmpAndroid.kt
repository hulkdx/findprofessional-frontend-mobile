import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

fun android(ext: CommonExtension<*, *, *, *, *, *>) = with(ext) {
    compileSdk = 34

    defaultConfig {
        minSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}
