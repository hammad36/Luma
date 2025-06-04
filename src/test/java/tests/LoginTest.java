package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ForgotYourPassword;
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


    @Test(dataProvider = "ValidLoginData", dataProviderClass = utils.TestDataProvider.class)
    public void logInWithValidCredentials(String email , String password , String expectedResult){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.fillLoginForm(email,password);
        LP.clickOnLoginButton();

        String actualResult = HP.verifyWelcomeMessage();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "inValidEmailData", dataProviderClass = utils.TestDataProvider.class)
    public void logInWithInvalidEmail(String email , String password , String expectedResult){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.fillLoginForm(email,password);
        LP.clickOnLoginButton();

        String actualResult = LP.errorMessageForInvalidData();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "inValidPasswordData", dataProviderClass = utils.TestDataProvider.class)
    public void logInWithInvalidPassword(String email , String password , String expectedResult){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.fillLoginForm(email,password);
        LP.clickOnLoginButton();

        String actualResult = LP.errorMessageForInvalidData();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "emptyLoginFieldsData", dataProviderClass = utils.TestDataProvider.class)
    public void logInWithEmptyFields(String email , String password , String expectedResult){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.fillLoginForm(email,password);
        LP.clickOnLoginButton();

        String actualResult = LP.errorMessageForEmptyFields();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "forgetPasswordData", dataProviderClass = utils.TestDataProvider.class)
    public void verifyForgotPasswordFunctionality(String expectedResult){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.forgotYourPassword();

        ForgotYourPassword FYP = new ForgotYourPassword();
        String actualResult = FYP.verifyForgotPasswordPageRedirection();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "forgetPasswordData", dataProviderClass = utils.TestDataProvider.class)
    public void verifyCreateAccountButtonRedirect(){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();

        ForgotYourPassword FYP = new ForgotYourPassword();
        FYP.verifyForgotPasswordPageRedirection();

    }


    @AfterClass
    public void tearDown() {
        BaseClass.getInstance().tearDown();
    }
}
