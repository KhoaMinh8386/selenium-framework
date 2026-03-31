package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials from GitHub Secrets")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        // 1. Đọc Username: Ưu tiên System property (-D), sau đó là Environment Variable (GitHub Secret)
        String username = System.getProperty("appUsername");
        if (username == null || username.isEmpty()) {
            username = System.getenv("SAUCEDEMO_USERNAME");
        }

        // 2. Đọc Password: Ưu tiên System property (-D), sau đó là Environment Variable (GitHub Secret)
        String password = System.getProperty("appPassword");
        if (password == null || password.isEmpty()) {
            password = System.getenv("SAUCEDEMO_PASSWORD");
        }

        // 3. Kiểm tra bảo mật: Nếu thiếu thông tin thì stop test ngay thay vì fallback giá trị cũ
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new RuntimeException("ERROR: SAUCEDEMO_USERNAME or SAUCEDEMO_PASSWORD is NOT configured in GitHub Secrets or Environment Variables!");
        }

        // 4. Thực hiện Login
        loginPage.login(username, password);

        // 5. Assert kết quả
        Assert.assertTrue(inventoryPage.isInventoryDisplayed(), "Inventory page was not displayed after login.");
        Assert.assertEquals(inventoryPage.getAppLogoText(), "Swag Labs", "Inventory logo header mismatch.");
    }

    @Test(description = "Verify error message with invalid credentials (not using secrets)")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Đối với test case thất bại, chúng ta vẫn dùng chuỗi cố định nhưng không phải thông tin thật
        loginPage.login("non_existent_user", "invalid_password");

        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Error message mismatch.");
    }
}
