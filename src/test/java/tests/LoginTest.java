package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.BaseClass;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest {
    WebDriver driver;

    @Parameters("appURL")
    @BeforeClass
    public void setUP(String appURL){
        BaseClass.getInstance().setUp();
        driver = BaseClass.getInstance().getDriver();
        driver.get(appURL);
    }

    @AfterClass
    public void tearDown() {
        BaseClass.getInstance().tearDown();
    }
}
