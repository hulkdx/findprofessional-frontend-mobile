[![kotlin](https://img.shields.io/badge/kotlin-2.0.0-blue.svg?logo=kotlin&style=for-the-badge)](http://kotlinlang.org)
[![deploy](https://img.shields.io/github/actions/workflow/status/hulkdx/findprofessional-frontend-mobile/push.yml?style=for-the-badge)](https://github.com/hulkdx/findprofessional-frontend-mobile/actions/workflows/push.yml)

# Find Professional

## Architecture

### Tech stack

- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform); for shared UI
- [Decompose](https://github.com/arkivanov/Decompose); for navigation
- [Ktor](https://github.com/ktorio/ktor); for networking
- [Koin](https://github.com/InsertKoinIO/koin); for dependency injection

## CI

### Android
A push to `main` branch will release it to [Google Play](https://play.google.com/store/apps/details?id=com.hulkdx.findprofessional)

## iOS
Under development

## Troubleshooting

### Running in Xcode - Java mismatch
If you want to run the code in xcode and java version mismatches, run the following line:
```sh
echo JAVA_HOME=\'/Applications/Android Studio.app/Contents/jbr/Contents/Home\' > ios/.env
```

## Improvement idea

- Use [this](https://arkivanov.github.io/Decompose/tips-tricks/composable-viewmodel/#composable-functions-with-viewmodels-with-savedstatehandle) guide if you need SavedStateHandle in ViewModel of Decompose.
- Use [compose-navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html) once its not `experimental` anymore.
