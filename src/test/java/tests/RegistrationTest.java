package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MyAccountPage;
import utils.BaseClass;
import utils.TestDataProvider;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

public class RegistrationTest {
    WebDriver driver;

    @Parameters("appURL")
    @BeforeClass
    public void setUP(String appURL){
        BaseClass.getInstance().setUp();
        driver = BaseClass.getInstance().getDriver();
        driver.get(appURL);
    }


    @Test(dataProvider = "validSignupData", dataProviderClass = utils.TestDataProvider.class)
    public void verifySignupWithValidData(String firstName , String lastName , String email , String password
            ,String confirmPassword ,String expectedResult) throws InterruptedException {

        HomePage HP = new HomePage();
        HP.clickOnCreateAnAccount();
        RegistrationPage RP = new RegistrationPage();
        RP.fillRegistrationForm(firstName, lastName, email, password, confirmPassword);
        RP.clickOnRegisterButton();

        MyAccountPage accountPage = new MyAccountPage();
        String actualResult = accountPage.getSuccessMessage();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "weakPasswordData", dataProviderClass = utils.TestDataProvider.class)
    public void verifySignupWithLowPasswordStrength(String firstName , String lastName , String email , String password
            ,String confirmPassword , String expectedResult) throws InterruptedException {

        HomePage HP = new HomePage();
        HP.clickOnCreateAnAccount();
        RegistrationPage RP = new RegistrationPage();
        RP.fillRegistrationForm(firstName, lastName, email, password, confirmPassword);
        RP.clickOnRegisterButton();
        String actualResult = RP.errorMessageForLowStrengthPassword();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "passwordComplexityInvalidData", dataProviderClass = utils.TestDataProvider.class)
    public void passwordComplexityRequirement(String firstName , String lastName , String email , String password
            ,String confirmPassword , String expectedResult) throws InterruptedException {

        HomePage HP = new HomePage();
        HP.clickOnCreateAnAccount();
        RegistrationPage RP = new RegistrationPage();
        RP.fillRegistrationForm(firstName, lastName, email, password, confirmPassword);
        RP.clickOnRegisterButton();
        String actualResult = RP.errorMessageForLowStrengthPassword();
        Assert.assertEquals(actualResult,expectedResult);
    }


    @Test(dataProvider = "invalidEmailData", dataProviderClass = utils.TestDataProvider.class)
    public void verifySignupWithInvalidEmailFormat(String firstName , String lastName , String email , String password
            ,String confirmPassword , String expectedResult) {
        HomePage HP = new HomePage();
        HP.clickOnCreateAnAccount();
        RegistrationPage RP = new RegistrationPage();
        RP.fillRegistrationForm(firstName, lastName, email, password, confirmPassword);
        RP.clickOnRegisterButton();
        String actualResult = RP.errorMessageForInvalidEmail();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "emptyFieldsData", dataProviderClass = utils.TestDataProvider.class)
    public void verifySignupWithEmptyFields(String firstName , String lastName , String email , String password
            ,String confirmPassword , String expectedResult) {
        HomePage HP = new HomePage();
        HP.clickOnCreateAnAccount();
        RegistrationPage RP = new RegistrationPage();
        RP.fillRegistrationForm(firstName, lastName, email, password, confirmPassword);
        RP.clickOnRegisterButton();
        String actualResult = RP.errorMessageForEmptyFields();
        Assert.assertEquals(actualResult.trim(), expectedResult.trim());
    }

    @Test(dataProvider = "passwordMismatchData", dataProviderClass = utils.TestDataProvider.class)
    public void verifySignupWithPasswordMismatch(String firstName , String lastName , String email , String password
            ,String confirmPassword , String expectedResult) {
        HomePage HP = new HomePage();
        HP.clickOnCreateAnAccount();
        RegistrationPage RP = new RegistrationPage();
        RP.fillRegistrationForm(firstName, lastName, email, password, confirmPassword);
        RP.clickOnRegisterButton();
        String actualResult = RP.errorMessageForPasswordMismatch();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "alreadyRegisteredEmailData", dataProviderClass = utils.TestDataProvider.class)
    public void verifySignupWithAlreadyRegisteredEmail(String firstName , String lastName , String email , String password
            ,String confirmPassword , String expectedResult) {
        HomePage HP = new HomePage();
        HP.clickOnCreateAnAccount();
        RegistrationPage RP = new RegistrationPage();
        RP.fillRegistrationForm(firstName, lastName, email, password, confirmPassword);
        RP.clickOnRegisterButton();
        String actualResult = RP.errorMessageForAlreadyRegisteredEmail();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(dataProvider = "longInputValuesData", dataProviderClass = utils.TestDataProvider.class)
    public void verifySignupWithLongInputValues(String firstName , String lastName , String email , String password
            ,String confirmPassword,String expectedResult) throws InterruptedException {
        HomePage HP = new HomePage();
        HP.clickOnCreateAnAccount();
        RegistrationPage RP = new RegistrationPage();
        RP.fillRegistrationForm(firstName, lastName, email, password, confirmPassword);
        RP.clickOnRegisterButton();
        String actualResult = RP.errorMessageForLongInputValues();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @AfterClass
    public void tearDown() {
        BaseClass.getInstance().tearDown();
    }
}
