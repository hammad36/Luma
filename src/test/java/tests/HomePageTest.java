package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.SignOutPage;
import utils.BaseClass;

import java.util.List;

public class HomePageTest {


    WebDriver driver;

    @Parameters("appURL")
    @BeforeClass()
    @Test(dataProvider = "ValidLoginData", dataProviderClass = utils.TestDataProvider.class)
    public void setUP(String appURL){
        BaseClass.getInstance().setUp();
        driver = BaseClass.getInstance().getDriver();
        driver.get(appURL);
        String email = "hammad32@gmail.com";
        String password = "Hammad@2533";
        String expectedResult = "Welcome, Mohammed Hammad!";
        LoginTest loginObject = new LoginTest();
        loginObject.logInWithValidCredentials(email , password , expectedResult);

    }


    @Test()
    public void testAddingProductToCart() {

        HomePage HP = new HomePage();

        List<WebElement> products = HP.getAllProductItems();
        Assert.assertFalse(products.isEmpty(), "No products found on Home Page!");

        HP.addSpecificProductToCartUsingHover(products.get(0), "M", "Blue");

        String expectedMessage = "You added Radiant Tee to your shopping cart.";
        String actualMessage = HP.getSuccessMessage();
        Assert.assertEquals(actualMessage,expectedMessage);

    }

    @Test()
    public void testAddMultipleProductsToCart() {
        HomePage HP = new HomePage();

        List<WebElement> products = HP.getAllProductItems();
        Assert.assertFalse(products.isEmpty(), "No products found on Home Page!");
        String expectedMessage1 = "You added Argus All-Weather Tank to your shopping cart.";
        String expectedMessage2 = "You added Radiant Tee to your shopping cart.";

        HP.addSpecificProductToCartUsingHover(products.get(2), "M", "Gray");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        HP.addSpecificProductToCartUsingHover(products.get(0), "S", "Blue");

        String actualMessage2 = HP.getSuccessMessage();
        Assert.assertEquals(actualMessage2,expectedMessage2);

    }

    @Test()
    public void testAddToCartWithoutSize() {
        HomePage HP = new HomePage();

        List<WebElement> products = HP.getAllProductItems();
        Assert.assertFalse(products.isEmpty(), "No products found on Home Page!");

        HP.addSpecificProductToCartUsingHover(products.get(3), "", "Green");

        String expectedMessage = "You need to choose options for your item.";
        ProductPage PG = new ProductPage();
        String actualMessage = PG.getErrorMessage();
        Assert.assertEquals(actualMessage,expectedMessage);

    }

    @Test()
    public void testAddToCartWithoutColor() {
        HomePage HP = new HomePage();

        List<WebElement> products = HP.getAllProductItems();
        Assert.assertFalse(products.isEmpty(), "No products found on Home Page!");

        HP.addSpecificProductToCartUsingHover(products.get(3), "L", "");

        String expectedMessage = "You need to choose options for your item.";
        ProductPage PG = new ProductPage();
        String actualMessage = PG.getErrorMessage();
        Assert.assertEquals(actualMessage,expectedMessage);

    }

    @Test()
    public void clearCart (){

        HomePage HP = new HomePage();

        HP.clickOnCartIcon();

        List<WebElement> cartItems = HP.getAllCartItems();

        for (WebElement item : cartItems) {
            HP.deleteItems();
        }
    }

    @Test()
    public void navigateToMyAccountPage() {

        HomePage HP = new HomePage();

        HP.clickOnMyAccount();

        String expectedPageTitle = "My Account";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle,expectedPageTitle);
    }

    @Test()
    public void navigateToMyWishList() {

        HomePage HP = new HomePage();

        HP.clickOnMyWishList();

        String expectedPageTitle = "My Wish List";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle,expectedPageTitle);
    }

    @Test()
    public void clickOnSignOut() {
        HomePage HP = new HomePage();

        HP.signOut();
        String expectedTitle = "You are signed out";
        SignOutPage SOP = new SignOutPage();
        String actualTitle =SOP.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

    }

    @AfterClass
    public void tearDown() {
        BaseClass.getInstance().tearDown();
    }
}


