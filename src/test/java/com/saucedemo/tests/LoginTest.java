package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private String getUsername() {
        String u = System.getProperty("appUsername");
        return (u != null && !u.isEmpty()) ? u : System.getenv("SAUCEDEMO_USERNAME");
    }

    private String getPassword() {
        String p = System.getProperty("appPassword");
        return (p != null && !p.isEmpty()) ? p : System.getenv("SAUCEDEMO_PASSWORD");
    }

    @Test(priority = 1)
    public void test1() {
        new LoginPage(getDriver()).login(getUsername(), getPassword());
        Assert.assertTrue(new InventoryPage(getDriver()).isInventoryDisplayed());
    }

    @Test(priority = 2)
    public void test2() {
        new LoginPage(getDriver()).login("problem_user", "secret_sauce");
        Assert.assertTrue(new InventoryPage(getDriver()).isInventoryDisplayed());
    }

    @Test(priority = 3)
    public void test3() {
        new LoginPage(getDriver()).login("visual_user", "secret_sauce");
        Assert.assertTrue(new InventoryPage(getDriver()).isInventoryDisplayed());
    }

    @Test(priority = 4)
    public void test4() {
        new LoginPage(getDriver()).login("error_user", "secret_sauce");
        Assert.assertTrue(new InventoryPage(getDriver()).isInventoryDisplayed());
    }

    @Test(priority = 5)
    public void test5() {
        new LoginPage(getDriver()).login("performance_glitch_user", "secret_sauce");
        Assert.assertTrue(new InventoryPage(getDriver()).isInventoryDisplayed());
    }

    @Test(priority = 6)
    public void test6() {
        new LoginPage(getDriver()).login("locked_out_user", "secret_sauce");
        Assert.assertTrue(new LoginPage(getDriver()).getErrorMessage().contains("locked out"));
    }

    @Test(priority = 7)
    public void test7() {
        new LoginPage(getDriver()).login("unknown", "unknown");
        Assert.assertTrue(new LoginPage(getDriver()).getErrorMessage().contains("do not match"));
    }

    @Test(priority = 8)
    public void test8() {
        new LoginPage(getDriver()).login("", "");
        Assert.assertTrue(new LoginPage(getDriver()).getErrorMessage().contains("is required"));
    }
}
