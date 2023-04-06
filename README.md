[![kotlin](https://img.shields.io/badge/kotlin-1.8.10-blue.svg?logo=kotlin&style=for-the-badge)](http://kotlinlang.org)
[![deploy](https://img.shields.io/github/actions/workflow/status/hulkdx/findprofessional-frontend-mobile/push.yml?style=for-the-badge)](https://github.com/hulkdx/findprofessional-frontend-mobile/actions/workflows/push.yml)

# Find Professional

## Design
https://www.figma.com/file/59wAYmh2zv94mUeA7udBzc/Login-Screen?node-id=0%3A1

## Android

### CI
A push to `main` branch will release it to [Google Play](https://play.google.com/store/apps/details?id=com.hulkdx.findprofessional)

### Dependency Check
```sh
./gradlew dependencyUpdates
```

## iOS

### CI
Under development

## Code

### Structure

View: Jetpack compose function for Android and SwiftUI for iOS.

ViewModel: Android / iOS specific ViewModels act only as a proxy between view and usecase, should not contain login in it.

UseCase: The domain layer; encapsulates the business logic of our app and requests data from the repositories. It is a common code (KMM) for both android and iOS.

Repository: Fetches data either from a remote or local data source and exposes them to our domain layer. It only talks to abstractions so that it won’t violate the Dependency Inversion Principle. It is a common code (KMM) for both android and iOS.

