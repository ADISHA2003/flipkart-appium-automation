# Flipkart Appium Automation

Android UI automation for the Flipkart app using Appium + Java + TestNG, structured with the Page Object Model.

## Tech Stack

- Java 17
- Maven
- Appium Java Client 9.2.3 (UiAutomator2)
- Selenium 4.21.0 (pinned — see [pom.xml](pom.xml))
- TestNG 7.10.2
- Jackson (JSON config)
- SLF4J Simple (logging)
- Extent Reports

## Project Structure

```
Flipkart-Appium-Automation/
├── pom.xml
├── testng.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── pages/
    │   │   │   ├── BasePage.java
    │   │   │   └── Android/
    │   │   │       ├── BasePage.java
    │   │   │       ├── HomePage.java
    │   │   │       ├── SearchPage.java
    │   │   │       ├── SearchResultsPage.java
    │   │   │       ├── ProductPage.java
    │   │   │       └── CartPage.java
    │   │   └── utils/
    │   │       ├── DriverManager.java
    │   │       └── TestDataReader.java
    │   └── resources/
    │       ├── config.json
    │       └── testdata.properties
    └── test/
        └── java/
            └── tests/
                ├── BaseTest.java
                └── FlipkartTest.java
```

## Prerequisites

- JDK 17
- Maven 3.8+
- Node.js + Appium Server 2.x
- UiAutomator2 driver: `appium driver install uiautomator2`
- Android SDK + `adb` on PATH
- A connected Android device or emulator with the Flipkart app installed

## Configuration

[src/main/resources/config.json](src/main/resources/config.json) — Appium / device capabilities:

```json
{
  "appiumServerUrl": "http://127.0.0.1:4723",
  "platformName": "Android",
  "automationName": "UiAutomator2",
  "deviceName": "Android",
  "udid": "<your-device-udid>",
  "appPackage": "com.flipkart.android",
  "appActivity": "com.flipkart.android.activity.HomeFragmentHolderActivity"
}
```

Get the device UDID with `adb devices` and update `udid`.

[src/main/resources/testdata.properties](src/main/resources/testdata.properties) — test inputs:

```
searchQuery=iphone17
searchSuggestionText=iphone 17, in Mobiles
```

## Running Tests

The Appium server is started/stopped automatically by [BaseTest.java](src/test/java/tests/BaseTest.java) via `AppiumServiceBuilder`. No need to launch it manually.

```bash
mvn clean test
```

Default platform is Android (`an`). Override at runtime:

```bash
mvn clean test -Dplatform=an
```

## Test Flow

[FlipkartTest.placeOrderFlow](src/test/java/tests/FlipkartTest.java) covers:

1. Verify search bar on Home
2. Enter search query
3. Pick the matching suggestion
4. Open the first search result
5. Open Cart from product page
6. Skip add-ons
7. Place Order
8. Continue

## Reports

- TestNG: `target/surefire-reports/index.html`
- Emailable: `target/surefire-reports/emailable-report.html`

## Notes

- `noReset=true` keeps app state between runs; `appium:forceAppLaunch=true` forces the configured activity each session — see [DriverManager.java](src/main/java/utils/DriverManager.java).
- iOS path is stubbed in `DriverManager.getIosDriver()` but not implemented.
