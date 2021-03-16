# Skilos
### Greek for dog

This project is an Android application that fetches a list of dog breeds and subbreeds.

## Features
* Clean Architecture with MVVM
* Kotlin Coroutines with Flow
* Dagger Hilt
* Kotlin Gradle DSL
* Git pre-commit hook powered by Ktlint
* Github actions that runs on every PR raised

## Prerequisite
To build this project, you require:
- Android Studio 4.2 Beta 4 or Android Studo Artic Fox
- Gradle gradle-6.7.1

## Libraries

- [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Presenter for persisting view state across config changes
- [Room](https://developer.android.com/training/data-storage/room) - Provides abstraction layer over SQLite
- [Navigation Component](https://developer.android.com/guide/navigation) - Helper Jetpack library to handle activity/fragment navigation
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines
- [kotlinx.coroutines.test](https://github.com/Kotlin/kotlinx.coroutines) - Library Support with `runBlockingTest` coroutine builder used in tests
- [Kluent](https://github.com/MarkusAmshove/Kluent) - "Fluent Assertions" library written specifically for Kotlin.
- [Turbine](https://github.com/cashapp/turbine) - Turbine is a small testing library for kotlinx.coroutines Flow.
- [Robolectric](http://robolectric.org/) - Unit test on android framework.
- [AndroidX Test](https://developer.android.com/training/testing/set-up-project) - Utility libraries/wrappers for Test on Android
- [Ktlint](https://github.com/pinterest/ktlint) - Linter for Kotlin projects
- [Dagger Hilt](https://dagger.dev/hilt) - handles dependency injection
- [Kotlin Gradle DSL](https://guides.gradle.org/migrating-build-logic-from-groovy-to-kotlin)
- [MockK](https://github.com/mockk/mockk) - Mocking library powered by Kotlin