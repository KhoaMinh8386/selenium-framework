package com.saucedemo.base;

import com.saucedemo.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    // Sử dụng ThreadLocal để hỗ trợ chạy song song
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    @Parameters({"browser", "env"})
    public void setUp(String browserFromXml, String envParam) {
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = browserFromXml;
        }
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }
        WebDriver webDriver = DriverFactory.createDriver(browser);
        driver.set(webDriver);
        getDriver().get("https://www.saucedemo.com/");
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    private void takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path destination = Paths.get("target", "screenshots", testName + "_" + timestamp + ".png");
        try {
            Files.createDirectories(destination.getParent());
            Files.copy(source.toPath(), destination);
            System.out.println("Screenshot captured: " + destination.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed: " + e.getMessage());
        }
    }
}
