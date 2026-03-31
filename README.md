# Selenium Automation Framework - Saucedemo

This is a complete Selenium automation project using **Java 17**, **Maven**, and **TestNG**. 
It implements the **Page Object Model (POM)** and is ready for **GitHub Actions** CI/CD integration.

## 🚀 Getting Started

### Prerequisites

- **Java JDK 17** (or higher)
- **Maven**
- **Google Chrome** or **Firefox**

### Local Execution

1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd selenium-framework
   ```

2. Run tests via Maven command:
   ```bash
   mvn clean test -Dbrowser=chrome -Denv=dev -DsuiteXmlFile=testng-smoke.xml
   ```

3. View reports:
   - Test execution results: `target/surefire-reports/index.html`
   - Screenshots on failure: `target/screenshots/`

---

## 🛠 Project Structure

- `src/main/java/com/saucedemo/driver/DriverFactory.java`: Handles WebDriver initialization (including headless mode for CI).
- `src/main/java/com/saucedemo/pages/`: Contains Page Object classes for `LoginPage` and `InventoryPage`.
- `src/test/java/com/saucedemo/base/BaseTest.java`: Setup/teardown logic and screenshot functionality.
- `src/test/java/com/saucedemo/tests/LoginTest.java`: Smoke tests for login functionality.
- `testng-smoke.xml`: Test suite configuration.
- `.github/workflows/selenium-ci.yml`: GitHub Actions CI pipeline configuration.

---

## ☁️ Continuous Integration (GitHub Actions)

This project is configured to run tests automatically on:
- **Push** to `main` or `develop` branches.
- **Pull Request** to `main` branch.
- **Workflow Dispatch** (manual trigger).

### Requirement for CI Success
- Set a **Repository Secret** named `SAUCEDEMO_PASSWORD` in your GitHub repository setting (`Settings > Secrets and variables > Actions`).
- Value: `secret_sauce`

---

## ✨ Features
- **Headless Mode**: Detected automatically when running in GitHub Actions environment.
- **Failure Analysis**: Screenshots are automatically captured on test failure and uploaded as artifacts in CI.
- **Data-Driven-Ready**: Uses System Properties and Environment Variables for sensitive data.

---
**Author:** Senior QA Automation Engineer
