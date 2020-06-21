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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {
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
    public void basicSearchWithEmptyInputs() {
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

    @Test
    public void basicSearchWithValidInputs() {
        driver.get("https://workdemo.me/workzy/");
        driver.manage().window().setSize(new Dimension(1079, 964));
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("intro-keywords")));
        }
        driver.findElement(By.id("intro-keywords")).click();
        driver.findElement(By.id("intro-keywords")).sendKeys("test");
        driver.findElement(By.id("search_location")).click();
        driver.findElement(By.id("search_location")).sendKeys("test");
        driver.findElement(By.id("select2-search_category-container")).click();
        driver.findElement(By.cssSelector("i:nth-child(2)")).click();
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search_keywords > #search_keywords")));
        }
        {
            String value = driver.findElement(By.cssSelector(".search_keywords > #search_keywords")).getAttribute("value");
            assertThat(value, is("test"));
        }
        {
            String value = driver.findElement(By.id("search_location")).getAttribute("value");
            assertThat(value, is("test"));
        }
    }
}
