package tests;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.ForgotYourPassword;
import pages.RegistrationPage;
import utils.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import utils.ExtentManager;

public class LoginTest {
    WebDriver driver;
    ExtentReports extent = ExtentManager.getReportObject();


    @Parameters("appURL")
    @BeforeClass
    public void setUP(String appURL){
        BaseClass.getInstance().setUp();
        driver = BaseClass.getInstance().getDriver();
        driver.get(appURL);
    }


    @Test(dataProvider = "inValidEmailData", groups = {"smoke"} , dataProviderClass = utils.TestDataProvider.class)
    public void logInWithValidCredentials(String email , String password , String expectedResult){
        SoftAssert softAssert = new SoftAssert();
        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.fillLoginForm(email,"MMMgfdsasdd@rter323");
        LP.clickOnLoginButton();

        String actualResult = HP.verifyWelcomeMessage();
        softAssert.assertEquals(actualResult,expectedResult);
        softAssert.assertAll();
    }

    @Test(dataProvider = "inValidEmailData" , groups = {"smoke"}, dataProviderClass = utils.TestDataProvider.class)
    public void logInWithInvalidEmail(String email , String password , String expectedResult){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.fillLoginForm(email,password);
        LP.clickOnLoginButton();

        String actualResult = LP.errorMessageForInvalidData();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "inValidPasswordData", groups = {"smoke"}, dataProviderClass = utils.TestDataProvider.class)
    public void logInWithInvalidPassword(String email , String password , String expectedResult){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.fillLoginForm(email,password);
        LP.clickOnLoginButton();

        String actualResult = LP.errorMessageForInvalidData();
        Assert.assertEquals(actualResult,expectedResult);
        extent.flush();
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

    @Test(dataProvider = "createAnAccountData", dataProviderClass = utils.TestDataProvider.class)
    public void verifyCreateAccountButtonRedirect(String expectedResult){

        HomePage HP = new HomePage();
        HP.clickOnSignIn();

        LoginPage LP = new LoginPage();
        LP.clickOnCreateAnAccountButton();

        RegistrationPage RP = new RegistrationPage();
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);

    }


    @AfterClass
    public void tearDown() {
        BaseClass.getInstance().tearDown();
    }
}
