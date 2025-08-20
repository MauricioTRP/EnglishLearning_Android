plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.room)
    alias(libs.plugins.serialization)
    alias(libs.plugins.protobuf)
}

room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "com.kotlinpl.english_learning"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kotlinpl.english_learning"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString() // Get artifact version from libs.versions.toml
    }

    // Generate java protobuf-lite for protobuf in this project
    generateProtoTasks {
        all().configureEach {
            builtins {
                try {
                    create("java") {
                        option("lite")
                    }
                } catch (_: Exception) {
                    getByName("java") {
                        option("lite")
                    }
                }
            }
        }

    }
}

dependencies {
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.bundles.room.db)

    implementation(libs.bundles.network)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.protobuf)
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)

    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit.android.runner)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}