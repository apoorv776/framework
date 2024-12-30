

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;


public class AllureReportTest {
    public static WebDriver driver;

    @BeforeClass
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Set the driver for AllureReportListener
        
    }

    @Test(priority = 1)
    public void initialDemo() {
        driver.get("https://www.google.co.in/");
        System.out.println("Page title is: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Google"), "Title should contain 'Google'");
    }

    @Test(priority = 2)
    public void testGoogleSearchPass1() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("TestNG");
        searchBox.submit();
        WebElement resultElement = driver.findElement(By.xpath("//div[text()='TestNG']"));
        System.out.println("Search result found: " + resultElement.getText());
        Assert.assertTrue(driver.getTitle().contains("TestNG"), "Title should contain 'TestNG'");
    }

    @Test(priority = 3)
    public void testGoogleSearchFail1() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("JUnit");
        searchBox.submit();

        // Intentionally fail this test by asserting a different expected title
        Assert.assertTrue(driver.getTitle().contains("TestNG"), "Title should contain 'TestNG'");
    }

    @Test(priority = 4)
    public void testGoogleSearchFail2() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Appium");
        searchBox.submit();

        // Intentionally fail this test by asserting a different expected title
        Assert.assertTrue(driver.getTitle().contains("Selenium"), "Title should contain 'Selenium'");
    }
}
