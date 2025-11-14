
# MobileHybridAutomation

Mobile-only hybrid automation with **Java 17**, **Appium 2.x (CLI)**, **Cucumber**, **TestNG**, and **ExtentReports**.
Runs **mobile web** (Chrome on Android) and **native app** (`ApiDemos-debug.apk`). Parallel-ready.

## Prereqs
- Java 11/17+
- Node + Appium 2.x (`npm i -g appium`)
- Android SDK (AVD + `adb`)
- Start emulator (e.g., `emulator-5554`)

## Start Appium (auto chromedriver)
```bash
appium --allow-insecure chromedriver_autodownload
```

## Configure
`src/test/resources/config.properties`
```properties
executionType=mobileWeb  # or nativeApp
deviceName=emulator-5554
platformName=Android
appPath=app/ApiDemos-debug.apk
appiumServer=http://127.0.0.1:4723
webUrl=https://www.ndosiautomation.co.za/
```

You can also control execution per-scenario using tags `@mobileWeb` / `@nativeApp`.

## Run
```bash
mvn clean test
```
- Parallel scenarios enabled
- Report: `Reports/ExtentReport_<timestamp>.html`
- Screenshots on failure: `Reports/screenshots/`

## CI
- Jenkinsfile: runs `mvn clean test` and publishes `Reports/ExtentReport_*.html`
- azure-pipelines.yml: runs Maven and uploads `Reports` as artifact

## Troubleshooting
- If Chrome doesn't launch: ensure Appium started with `--allow-insecure chromedriver_autodownload` and device visible in `adb devices`.
- If class errors: `mvn clean install -U`.
# MobileHybridAutomationFramework
