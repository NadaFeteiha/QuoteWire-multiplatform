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

                implementation("androidx.compose.ui:ui:1.3.3")
                implementation("androidx.compose.ui:ui-tooling:1.3.3")
                implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
                implementation("androidx.compose.foundation:foundation:1.3.1")
                implementation("androidx.compose.material:material:1.3.1")
                implementation("androidx.activity:activity-compose:1.6.1")

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
