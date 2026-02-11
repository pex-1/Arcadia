import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.room)
    alias(libs.plugins.hilt)
}

android {

    namespace = "com.example.arcadia"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.arcadia"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        val localProperties = Properties().apply {
            load(rootProject.file("local.properties").inputStream())
        }

        buildConfigField("String", "API_KEY", "\"${localProperties["API_KEY"]}\"")
        buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
}
kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}
room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    //Androidx ktx
    implementation(libs.bundles.androidx.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.junit)

    //TODO: check for another solution
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    //Android test
    androidTestImplementation(libs.bundles.android.test)

    //Compose debug
    debugImplementation(libs.bundles.compose.debug)

    //Splash screen
    implementation(libs.androidx.core.splashscreen)

    //Coil
    implementation(libs.coil.compose)

    //Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    //Compose all dependencies
    implementation(libs.bundles.compose)

    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    //Paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    //Timber
    implementation(libs.timber)

    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.moshi)

    // Moshi
    implementation(libs.moshi.core)
    implementation(libs.moshi.kotlin)

    // OkHttp
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)

    //ProcessLifecycleOwner provides a lifecycle for the whole application process
    implementation(libs.androidx.lifecycle.process)
}