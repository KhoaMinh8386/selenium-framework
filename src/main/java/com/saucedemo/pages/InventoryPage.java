package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By APP_LOGO = By.className("app_logo");
    private final By INVENTORY_CONTAINER = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isInventoryDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(INVENTORY_CONTAINER)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getAppLogoText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(APP_LOGO)).getText();
    }
}
