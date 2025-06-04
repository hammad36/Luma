package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    public HomePage() {
        this.driver = BaseClass.getInstance().getDriver();
    }

    public void clickOnCreateAnAccount(){
//        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)")));
        driver.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)")).click();
    }

    public void clickOnSignIn(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("div[class='panel header'] li[data-label='or'] a"))));
        driver.findElement(By.cssSelector("div[class='panel header'] li[data-label='or'] a")).click();
    }
    public String verifyWelcomeMessage(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//div[@class='panel header']//span[@class='logged-in'][normalize-space()='Welcome, Mohammed Hammad!']"))));
        return driver.findElement(By.xpath("//div[@class='panel header']//span[@class='logged-in'][normalize-space()='Welcome, Mohammed Hammad!']")).getText();
    }

}
