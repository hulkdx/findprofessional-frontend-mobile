# Screenshot tests

It is using [paparazzi](https://github.com/cashapp/paparazzi) test library to run the screenshot tests.

It is used in seperate module so that build/assemble of the app is independet of this module.

## Scripts to run

```shell
# record the screenshot
./gradlew recordPaparazziDebug
# verify it
./gradlew verifyPaparazziDebug
```

## TODO
- Currently it is using android emulator to run it, it would be nice that it could run both android/ios.
