package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ForgotYourPassword;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.BaseClass;

import java.util.List;

public class HomePageTest {


    WebDriver driver;

    @Parameters("appURL")
    @BeforeClass
    public void setUP(String appURL){
        BaseClass.getInstance().setUp();
        driver = BaseClass.getInstance().getDriver();
        driver.get(appURL);

    }


    @Test(dataProvider = "ValidLoginData", dataProviderClass = utils.TestDataProvider.class)
    public void testAddToCartFromHomeHover(String email, String password , String expectedResult) {
        LoginTest loginObject = new LoginTest();
        loginObject.logInWithValidCredentials(email , password , expectedResult);
        HomePage HP = new HomePage();

        List<WebElement> products = HP.getAllProductItems();
        Assert.assertFalse(products.isEmpty(), "No products found on Home Page!");

        // نجرب نضيف أول منتج بالطريقة دي
        HP.addSpecificProductToCartUsingHover(products.get(0), "M", "Blue");

        String expectedMessage = "You added Radiant Tee to your shopping cart.";
        String actualMessage = HP.getSuccessMessage();
        Assert.assertEquals(actualMessage,expectedMessage);

    }

    @Test(dataProvider = "ValidLoginData", dataProviderClass = utils.TestDataProvider.class)
    public void clearCart (String email, String password , String expectedResult){
        LoginTest loginObject = new LoginTest();
        loginObject.logInWithValidCredentials(email , password , expectedResult);
        HomePage HP = new HomePage();

        HP.clickOnCartIcon();

        // نحصل على كل عناصر الكارت
        List<WebElement> cartItems = HP.getAllCartItems();

        // نحذف كل عنصر
        for (WebElement item : cartItems) {
            HP.deleteItems(); // أو الزرار اللي بيشيل المنتج
        }
    }

    @Test(dataProvider = "ValidLoginData", dataProviderClass = utils.TestDataProvider.class)
    public void navigateToMyAccountPage(String email, String password , String expectedResult) {
        LoginTest loginObject = new LoginTest();
        loginObject.logInWithValidCredentials(email, password, expectedResult);
        HomePage HP = new HomePage();

        HP.clickOnMyAccount();
    }

    @Test(dataProvider = "ValidLoginData", dataProviderClass = utils.TestDataProvider.class)
    public void navigateToMyWishList(String email, String password , String expectedResult) {
        LoginTest loginObject = new LoginTest();
        loginObject.logInWithValidCredentials(email, password, expectedResult);
        HomePage HP = new HomePage();

        HP.clickOnMyWishList();
    }

    @Test(dataProvider = "ValidLoginData", dataProviderClass = utils.TestDataProvider.class)
    public void clickOnSignOut(String email, String password , String expectedResult) {
        LoginTest loginObject = new LoginTest();
        loginObject.logInWithValidCredentials(email, password, expectedResult);
        HomePage HP = new HomePage();

        HP.signOut();
    }

    @AfterClass
    public void tearDown() {
        BaseClass.getInstance().tearDown();
    }
}


    /*

    public void verifyAddToCartFromHomePageUsingHover() {}
public void verifySizeAndColorOptionsAppearOnHover() {}
public void verifyPreventAddToCartWithoutSizeOrColor() {}
public void verifyCorrectSizeAndColorAddedToCart() {}
public void verifyMultipleProductsAddedUsingHover() {}
public void verifyNavigationBarIsVisibleOnHomePage() {}




     */
