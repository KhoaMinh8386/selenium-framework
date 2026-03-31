package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        String username = "standard_user";
        // Get password from System property (Maven -DappPassword) or Environment
        // Variable
        String password = System.getProperty("appPassword");
        if (password == null || password.isEmpty()) {
            password = System.getenv("APP_PASSWORD");
        }

        // Handle case where password is unknown/missing
        if (password == null || password.isEmpty()) {
            System.err.println("WARNING: APP_PASSWORD is not set. Using hardcoded password.");
            password = "secret_sauce";
        }

        loginPage.login(username, password);

        Assert.assertTrue(inventoryPage.isInventoryDisplayed(), "Inventory page was not displayed after login.");
        Assert.assertEquals(inventoryPage.getAppLogoText(), "Swag Labs", "Inventory logo header mismatch.");
    }

    @Test(description = "Verify error message with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("locked_out_user", "wrong_password");

        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Error message mismatch.");
    }
}
