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
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    public void clickOnLoginButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")));
        driver.findElement(By.xpath("//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")).click();
    }

    public void forgotYourPassword(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='action remind']//span[contains(text(),'Forgot Your Password?')]")));
        driver.findElement(By.xpath("//a[@class='action remind']//span[contains(text(),'Forgot Your Password?')]")).click();
    }

    public void clickOnCreateAnAccountButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//a[@class='action create primary']//span[contains(text(),'Create an Account')]"))));
        driver.findElement(By.xpath("//a[@class='action create primary']//span[contains(text(),'Create an Account')]")).click();
    }

    public String errorMessageForInvalidData(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"))));
        return driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
    }

    public String errorMessageForEmptyFields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        StringBuilder errors = new StringBuilder();

        try {
            String emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-error"))).getText();
            errors.append(emailError).append("\n");
        } catch (Exception ignored) {}

        try {
            String passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass-error"))).getText();
            errors.append(passwordError).append("\n");
        } catch (Exception ignored) {}


        return errors.toString().trim();
    }
}
