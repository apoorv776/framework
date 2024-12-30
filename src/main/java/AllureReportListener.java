

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class AllureReportListener implements ITestListener {
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional setup actions can go here, such as logging environment information
    }

    @Override
    public void onTestStart(ITestResult result) {
        // No specific actions needed for test start
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        captureScreenshot(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result.getMethod().getMethodName());
        logError(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logError(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (driver != null) {
            driver.quit();
        }
    }

    // Capture and attach a screenshot for Allure report
    @Attachment(value = "{testCaseName} Screenshot", type = "image/png")
    public byte[] captureScreenshot(String testCaseName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            return FileUtils.readFileToByteArray(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Log error details to Allure
    @Step("{error}")
    public void logError(Throwable error) {
        if (error != null) {
            error.printStackTrace();
        }
    }
}
