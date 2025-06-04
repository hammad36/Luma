package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    public LoginPage(){
        this.driver =  BaseClass.getInstance().getDriver();
    }

    public void fillLoginForm(String email , String password){
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickOnLoginButton(){
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void signInWithGoogle (){
        driver.findElement(By.xpath("//button[normalize-space()='Sign in with Google']")).click();
    }

    public void forgotYourPassword(){
        driver.findElement(By.xpath("//a[normalize-space()='Forgot your Password?']")).click();
    }

    public void clickOnRegistration(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//a[normalize-space()='Register your account']"))));
        driver.findElement(By.xpath("//a[normalize-space()='Register your account']")).click();
    }
}
