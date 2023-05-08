plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))

                val koinComposeVersion = "3.4.1"
                val coilVersion = "2.2.2"
                val accompanistVersion = "0.28.0"
                val navVersion = "2.5.3"

                implementation("androidx.compose.ui:ui:1.4.3")
                implementation("androidx.compose.ui:ui-tooling:1.4.3")
                implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
                implementation("androidx.compose.foundation:foundation:1.4.3")
                implementation("androidx.activity:activity-compose:1.7.1")
                implementation("androidx.compose.material3:material3")
                implementation(platform("androidx.compose:compose-bom:2022.10.00"))

                implementation ("com.airbnb.android:lottie-compose:4.2.0")
                implementation ("com.airbnb.android:lottie:6.0.0")

                implementation("io.insert-koin:koin-androidx-compose:$koinComposeVersion")
                implementation("io.coil-kt:coil-compose:$coilVersion")
                implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
                implementation("androidx.navigation:navigation-compose:$navVersion")

            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.myapplication.MyApplication"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
