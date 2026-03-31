package utils;

import bai1_base.base.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

/**
 * Listener để chụp ảnh màn hình và đính kèm vào Allure Report khi test thất bại.
 */
public class ScreenshotOnFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        if (BaseTest.getDriver() != null) {
            System.out.println("[ALLURE] Test failed: " + result.getName() + " - Attaching screenshot...");
            byte[] screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        }
    }
}
