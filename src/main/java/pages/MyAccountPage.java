package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.time.Duration;

public class MyAccountPage {
    WebDriver driver;

    public MyAccountPage() {
        this.driver = BaseClass.getInstance().getDriver();
    }

    public String getSuccessMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.message-success.success.message")));
        return driver.findElement(By.cssSelector("div.message-success.success.message")).getText();
    }
}
