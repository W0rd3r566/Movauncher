# Movauncher

A minimal Android launcher with extensive customization, based on [ARK Launcher](https://github.com/jrs8205/ARK-launcher).

**0.1.0 — foundation in progress**. This is an independent, experimental fork maintained by [W0rd3r566](https://github.com/W0rd3r566), not affiliated with the original project.

## First milestone

- Android app ID `com.movauncher.launcher` (installs alongside ARK Launcher).
- Independent app name, adaptive icon and version number.
- Graphite and Mint palette choices, keeping the rest of the upstream options.
- Automated debug APK build in GitHub Actions.

Movauncher Studio's freeform visual layout editing, mixable design systems, animation settings and theme-profile sharing are **planned** and not yet implemented.

## Development

Built with Kotlin and Jetpack Compose. Android 11 (API 30) or later.

```sh
bash ./gradlew :app:assembleDebug
```

The output is `app/build/outputs/apk/debug/app-debug.apk`. When CI passes, the APK is also available as a workflow artifact under **Actions → Android debug APK**.

The internal `org.arkikeskus.launcher` source namespace and Gradle convention plugin names remain intact for build compatibility. They are not the installed Android application ID.

## Original project and license

This is a derivative of [jrs8205/ARK-launcher](https://github.com/jrs8205/ARK-launcher). Its contributors retain credit for their work; the original Apache-2.0 `LICENSE` and attribution `NOTICE` are preserved. The upstream donation/community links remain credited to their owners.

Report Movauncher bugs via [Issues](https://github.com/W0rd3r566/Movauncher/issues).
