package pl.pagisoft.workzy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicSearchTest {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void Search_With_Empty_Inputs_Test() {
        driver.get("https://workdemo.me/workzy/");
        driver.manage().window().setSize(new Dimension(1079, 964));
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i:nth-child(2)")));
        }
        driver.findElement(By.cssSelector("i:nth-child(2)")).click();
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("titlebar")));
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("h2:nth-child(2)"));
            assert (elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector(".search_keywords > #search_keywords"));
            assert (elements.size() > 0);
        }
    }
}
