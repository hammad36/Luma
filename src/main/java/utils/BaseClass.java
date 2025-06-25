package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    private static BaseClass instance;
    private WebDriver driver;

    private BaseClass() {
    }

    public static BaseClass getInstance() {
        if (instance == null) {
            instance = new BaseClass();
        }
        return instance;
    }

    public void setUp() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
