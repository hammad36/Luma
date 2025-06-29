package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    public ProductPage() {
        this.driver = BaseClass.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getErrorMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-notice.notice.message")));
        return driver.findElement(By.cssSelector(".message-notice.notice.message")).getText();
    }

}
