package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.time.Duration;

public class ForgotYourPassword {
    WebDriver driver;
    public ForgotYourPassword(){
        this.driver =  BaseClass.getInstance().getDriver();
    }

    public String verifyForgotPasswordPageRedirection(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='field note']")));
        return driver.findElement(By.xpath("//div[@class='field note']")).getText();
    }
}
