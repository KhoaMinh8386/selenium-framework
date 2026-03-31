package bai2_pom.tests;

import bai1_base.base.BaseTest;
import bai2_pom.pages.CartPage;
import bai2_pom.pages.InventoryPage;
import bai2_pom.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

/**
 * ✅ NÂNG CẤP ALLURE REPORT - Lab 9
 */
@Epic("SauceDemo System")
@Feature("Cart Feature")
@Listeners({utils.ScreenshotOnFailureListener.class})
public class CartTest extends BaseTest {

    private static final String VALID_USER = "standard_user";
    private static final String VALID_PASS = "secret_sauce";

    @Test(description = "Thêm sản phẩm vào giỏ hàng")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add to Cart")
    @Description("Kiểm tra việc thêm sản phẩm đầu tiên vào giỏ hàng và xác nhận badge count cập nhật.")
    public void testAddItemToCart() {
        Allure.step("Mở trang và đăng nhập bằng account: " + VALID_USER);
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login(VALID_USER, VALID_PASS);
        
        Allure.step("Click 'Add to Cart' cho sản phẩm đầu tiên");
        int count = inventoryPage.addFirstItemToCart().getCartItemCount();
        
        Allure.step("Xác nhận số lượng hiển thị trên Badge là 1");
        Assert.assertEquals(count, 1, "Số lượng trên Cart Badge phải là 1");
    }

    @Test(description = "Xóa sản phẩm khỏi giỏ hàng")
    @Severity(SeverityLevel.NORMAL)
    @Story("Remove from Cart")
    @Description("Kiểm tra quy trình thêm sản phẩm vào giỏ, sau đó vào giỏ hàng để xóa và xác nhận giỏ hàng trống.")
    public void testRemoveItemFromCart() {
        Allure.step("Đăng nhập và thêm sản phẩm, sau đó click icon Giỏ hàng");
        LoginPage loginPage = new LoginPage(getDriver());
        CartPage cartPage = loginPage.login(VALID_USER, VALID_PASS)
                .addFirstItemToCart()
                .goToCart();
        
        Allure.step("Tại trang Cart, click nút 'Remove'");
        cartPage.removeFirstItem();
        
        Allure.step("Xác nhận danh sách sản phẩm hiển thị là 0");
        Assert.assertEquals(cartPage.getItemCount(), 0, "Cart phải rỗng sau khi xóa");
    }

    @Test(description = "Xác nhận nhiều sản phẩm trong giỏ hàng")
    @Severity(SeverityLevel.MINOR)
    @Story("Multiple Items Verification")
    public void testAddMultipleItemsAndVerify() {
        Allure.step("Đăng nhập và thêm 2 sản phẩm cụ thể: Backpack, Bike Light");
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login(VALID_USER, VALID_PASS)
                .addItemByName("Sauce Labs Backpack")
                .addItemByName("Sauce Labs Bike Light");
        
        Allure.step("Kiểm tra Badge count hiển thị 2");
        Assert.assertEquals(inventoryPage.getCartItemCount(), 2, "Cart badge count phải bằng 2");
        
        Allure.step("Kiểm tra danh sách tên sản phẩm trong trang Cart");
        List<String> cartItemNames = inventoryPage.goToCart().getItemNames();
        Assert.assertTrue(cartItemNames.contains("Sauce Labs Backpack"), "Không có balo");
        Assert.assertTrue(cartItemNames.contains("Sauce Labs Bike Light"), "Không có đèn xe");
    }
}
