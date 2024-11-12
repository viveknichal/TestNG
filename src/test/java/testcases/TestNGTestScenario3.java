package testcases;

import Utilities.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class TestNGTestScenario3 {

    private WebDriver driver = null;
    public static String hubURL = "https://hub.lambdatest.com/wd/hub";

    @BeforeTest
    @Parameters({"browserName", "platformName", "browserVersion"})
    public void setupBrowser(String browserName, String platformName, String browserVersion) throws MalformedURLException {
        setUp(browserName, platformName, browserVersion);
        this.driver = driver();
    }

    @Test(timeOut = 20000)
    public void testNG_TC3() {
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.findElement(By.xpath("//a[contains(text(),'Javascript Alerts')]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alertButtonWE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='JavaScript Alerts']//button")));

        alertButtonWE.click();

        // Wait for the alert to be present
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        // Switch to the alert
        Alert alert = driver.switchTo().alert();

        // Get the text of the alert
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "I am an alert box!");
        // Click on the OK button in the alert
        alert.accept();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public void setUp(String browser, String platFormName, String browserVersion) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", browserVersion);
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "viveknichal14");
        ltOptions.put("accessKey", "bASgKNLjJD104uE23tE8CoJgAkOHqQbAciqQwdZbhcARtDMptD");
        ltOptions.put("build", "LambdaTestCertification_SeleniumJavaTestNG_Vivek");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", platFormName);
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.0.0");
        capabilities.setCapability("LT:Options", ltOptions);

        DriverFactory.getInstances().setDriver(new RemoteWebDriver(new URL(hubURL), capabilities));
    }

    public WebDriver driver() {
        return DriverFactory.getInstances().getDriver();
    }
}
