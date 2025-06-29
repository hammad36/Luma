package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.time.Duration;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;

    public RegistrationPage() {
        this.driver = BaseClass.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillRegistrationForm(String firstName , String lastName , String email , String password ,String confirmPassword){

        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password-confirmation")).sendKeys(confirmPassword);

    }

    public void clickOnRegisterButton(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")));
        driver.findElement(By.xpath("//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")).click();
    }

    public String errorMessageForLowStrengthPassword(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='password-error']")));
       return driver.findElement(By.xpath("//div[@id='password-error']")).getText();
    }

    public String errorMessageForInvalidEmail(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_address-error")));
       return driver.findElement(By.id("email_address-error")).getText();
    }

    public String errorMessageForEmptyFields() {
        StringBuilder errors = new StringBuilder();

        try {
            String firstnameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname-error"))).getText();
            errors.append(firstnameError).append("\n");
        } catch (Exception ignored) {}

        try {
            String lastnameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastname-error"))).getText();
            errors.append(lastnameError).append("\n");
        } catch (Exception ignored) {}

        try {
            String emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_address-error"))).getText();
            errors.append(emailError).append("\n");
        } catch (Exception ignored) {}

        try {
            String passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-error"))).getText();
            errors.append(passwordError).append("\n");
        } catch (Exception ignored) {}

        try {
            String passwordConfirmationError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-confirmation-error"))).getText();
            errors.append(passwordConfirmationError).append("\n");
        } catch (Exception ignored) {}

        return errors.toString().trim();
    }

    public String errorMessageForPasswordMismatch(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-confirmation-error")));
        return driver.findElement(By.id("password-confirmation-error")).getText();
    }

    public String errorMessageForAlreadyRegisteredEmail(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
        return driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
    }

    public String errorMessageForLongInputValues(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
        return driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
    }

}

