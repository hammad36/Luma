package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.time.Duration;

public class SignOutPage {

    WebDriver driver;
    WebDriverWait wait;

    public SignOutPage() {
        this.driver = BaseClass.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-ui-id='page-title-wrapper' and contains(text(),'You are signed out')]")));
        return driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper' and contains(text(),'You are signed out')]")).getText();
    }
}
