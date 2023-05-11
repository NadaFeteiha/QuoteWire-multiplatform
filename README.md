<h1 align="center">Quote Wire Kotlin Multiplatform</h1>
<p align="center">  
<i>“Think before you speak. Read before you think.” <i>

Quote Wire is a simple app that allows you to save and share quotes. It is built using Kotlin Multiplatform, so it works on both Android and iOS.

<p align="center">  
</p>
</p>

<img src="https://github.com/NadaFeteiha/QuoteWire-multiplatform/blob/main/android.gif" />

[![Final video of fixing issues in your code in VS Code](http://img.youtube.com/vi/IevWloTEUNI/maxresdefault.jpg)](https://www.youtube.com/embed/IevWloTEUNI?autoplay=1) 
    
## Built with
- [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAiAzKqdBhAnEiwAePEjktk3ROIIxTqejhHWkDEwSaQqoE6GgrNHM8iYKw8xHx5SPPDu0oJ_DxoC8LYQAvD_BwE&gclsrc=aw.ds) for UI design. 
- [SQLDelight](https://cashapp.github.io/sqldelight/2.0.0-alpha05/) for database management.
- [Ktor](https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html) for networking
- [Koin dependency injection](https://insert-koin.io/) for dependency injection.
- [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture for clean separation of concerns and easy testing.
- [Coroutines](https://developer.android.com/kotlin/coroutines) for asynchronous programming
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) for reactive programming

## Features
- [x] Get quotes from the internet
- [x] Save quotes
- [x] Share quotes
- [ ] View a list of your saved quotes
- [ ] Search for quotes

## Set up the environment

    To work with this template, you need the following:

* A machine running a recent version of macOS
* [Xcode](https://apps.apple.com/us/app/xcode/id497799835)
* [Android Studio](https://developer.android.com/studio)
* The [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile)
* The [CocoaPods dependency manager](https://kotlinlang.org/docs/native-cocoapods.html)

### Check your environment

    Before you start, use the [KDoctor](https://github.com/Kotlin/kdoctor) tool to ensure that your development environment is configured correctly:

1. Install KDoctor with [Homebrew](https://brew.sh/):

    ```text
    brew install kdoctor
    ```

2. Run KDoctor in your terminal:

    ```text
    kdoctor
    ```

   If everything is set up correctly, you'll see valid output:

   ```text
   Environment diagnose (to see all details, use -v option):
   [✓] Operation System
   [✓] Java
   [✓] Android Studio
   [✓] Xcode
   [✓] Cocoapods
   
   Conclusion:
     ✓ Your system is ready for Kotlin Multiplatform Mobile development!
   ```

Otherwise, KDoctor will highlight which parts of your setup still need to be configured and will suggest a way to fix them.

## Examine the project structure

Open the project in Android Studio and switch the view from **Android** to **Project** to see all the files and targets belonging to the project:

<img src="readme_images/open_project_view.png" height="300px">

Your Compose Multiplatform project includes 3 modules:

## shared
This is a Kotlin module that contains the logic common for both Android and iOS applications, that is, the code you share between platforms.

This shared module is also where you’ll write your Compose Multiplatform code. In shared/src/commonMain/kotlin/App.kt, you can find the shared root @Composable function for your app.

It uses Gradle as the build system. You can add dependencies and change settings in shared/build.gradle.kts. The shared module builds into an Android library and an iOS framework.

## androidApp
This is a Kotlin module that builds into an Android application. It uses Gradle as the build system. The androidApp module depends on and uses the shared module as a regular Android library.

## iosApp
This is an Xcode project that builds into an iOS application. It depends on and uses the shared module as a CocoaPods dependency.
  

## License: 

    Copyright 2023 Nada Feteiha

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

