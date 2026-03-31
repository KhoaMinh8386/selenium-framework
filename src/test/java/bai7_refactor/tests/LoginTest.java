package bai7_refactor.tests;

import bai1_base.base.BaseTest;
import bai7_refactor.pages.InventoryPage;
import bai7_refactor.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * ✅ NÂNG CẤP ALLURE REPORT - Lab 9
 */
@Epic("SauceDemo System")
@Feature("Login Feature")
@Listeners({utils.ScreenshotOnFailureListener.class})
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData",
          dataProviderClass = bai7_refactor.dataproviders.LoginDataProvider.class,
          description = "DDT Login Test with Allure Report")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User Authentication")
    @Description("Kiểm thử tính năng đăng nhập với nhiều kịch bản (Success/Failure) sử dụng DataProvider và Allure Annotations.")
    public void testLogin(String username, String password, boolean expectSuccess, String description) {
        Allure.step("Kịch bản: " + description);
        
        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Mở trang login (Bắt đầu tại URL cấu hình)");
        
        Allure.step("Nhập username: " + username);
        Allure.step("Nhập password: " + (password.isEmpty() ? "[empty]" : "********"));
        Allure.step("Click nút đăng nhập");

        if (expectSuccess) {
            InventoryPage inventoryPage = loginPage.login(username, password);
            
            Allure.step("Xác nhận chuyển sang trang Inventory (Kiểm tra hiển thị header)");
            Assert.assertTrue(inventoryPage.isLoaded(),
                "[" + description + "] — Trang Inventory phải được load sau khi đăng nhập thành công");
        } else {
            loginPage.loginExpectingFailure(username, password);
            
            Allure.step("Xác nhận hiển thị thông báo lỗi cho người dùng");
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                "[" + description + "] — Phải hiển thị thông báo lỗi");
        }
    }
}
