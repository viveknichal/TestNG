package testcases;

import Utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class TestNGTestScenario2 {

    private WebDriver driver = null;
    public static String hubURL = "https://hub.lambdatest.com/wd/hub";

    @BeforeTest
    @Parameters({"browserName", "platformName", "browserVersion"})
    public void setupBrowser(String browserName, String platformName, String browserVersion) throws MalformedURLException {
        setUp(browserName, platformName, browserVersion);
        this.driver = driver();
    }

    @Test(timeOut = 20000)
    public void testNG_TC2() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://www.lambdatest.com/selenium-playground/");
        // Wait for the document ready state to be complete
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        driver.findElement(By.xpath("//a[contains(text(),'Checkbox Demo')]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkBoxWE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("isAgeSelected")));

        checkBoxWE.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checked = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtAge")));

        softAssert.assertTrue(checked.isDisplayed());

        checkBoxWE = driver.findElement(By.id("isAgeSelected"));
        checkBoxWE.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        checked = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtAge")));

        softAssert.assertTrue(checked.isDisplayed(), "Verified Checkbox is unselected");


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
