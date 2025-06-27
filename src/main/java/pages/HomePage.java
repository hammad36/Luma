package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.time.Duration;
import java.util.List;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage() {
        this.driver = BaseClass.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOnCreateAnAccount(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)")));
        driver.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)")).click();
    }

    public void clickOnSignIn(){
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("div[class='panel header'] li[data-label='or'] a"))));
        driver.findElement(By.cssSelector("div[class='panel header'] li[data-label='or'] a")).click();
    }

    public String verifyWelcomeMessage(){
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//div[@class='panel header']//span[@class='logged-in'][normalize-space()='Welcome, Mohammed Hammad!']"))));
        return driver.findElement(By.xpath("//div[@class='panel header']//span[@class='logged-in'][normalize-space()='Welcome, Mohammed Hammad!']")).getText();
    }

    public List<WebElement> getAllProductItems(){
        return  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.cssSelector("li.product-item"))));
    }

    public List<WebElement> getAllAvailableSizes(){
        return driver.findElements(By.cssSelector(".swatch-attribute-options .swatch-option.text"));
    }

    public List<WebElement> selectALLColors(){
        return driver.findElements(By.cssSelector(".swatch-attribute-options .swatch-option.color"));
    }


    public List<WebElement> getAllCartItems(){
        return driver.findElements(By.cssSelector("ol#mini-cart li.product-item"));
    }




    public void chooseSizeByIndex(String label){
        for (WebElement size : getAllAvailableSizes()) {
            if (size.getText().equalsIgnoreCase(label)) {
                size.click();
                break;
            }
        }
    }

    public void selectColorByLabel(String label){
        for (WebElement size : selectALLColors()) {
            if (size.getText().equalsIgnoreCase(label)) {
                size.click();
                break;
            }
        }
    }

    public void addToCart(){
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector(".action.tocart.primary"))));
        driver.findElement(By.cssSelector(".action.tocart.primary")).click();

    }


    public void addSpecificProductToCartUsingHover(WebElement product, String sizeLabel, String colorLabel) {
        Actions actions = new Actions(driver);
        actions.moveToElement(product).pause(Duration.ofMillis(500)).perform();

        List<WebElement> sizes = product.findElements(By.cssSelector(".swatch-attribute.size .swatch-option"));
        if (!sizes.isEmpty()) {
            for (WebElement size : sizes) {
                if (size.getText().equalsIgnoreCase(sizeLabel)) {
                    size.click();
                    break;
                }
            }
        }

        List<WebElement> colors = product.findElements(By.cssSelector(".swatch-attribute.color .swatch-option"));
        if (!colors.isEmpty()) {
            for (WebElement color : colors) {
                String ariaLabel = color.getAttribute("aria-label");
                if (ariaLabel != null && ariaLabel.equalsIgnoreCase(colorLabel)) {
                    color.click();
                    break;
                }
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> addToCartButtons = product.findElements(By.cssSelector(".actions-primary .tocart"));
        if (!addToCartButtons.isEmpty()) {
            WebElement addToCartBtn = addToCartButtons.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
            addToCartBtn.click();
        } else {
            System.out.println("No Add to Cart button found for this product.");
        }
    }

    public String getSuccessMessage (){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.message-success.success.message > div")));
        return driver.findElement(By.cssSelector("div.message-success.success.message > div")).getText();
    }

    public void clickOnCartIcon(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.showcart")));
        driver.findElement(By.cssSelector(".action.showcart")).click();
    }

    public void deleteItems(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.delete")));
        driver.findElement(By.cssSelector(".action.delete")).click();
        driver.findElement(By.cssSelector(".action-primary.action-accept")).click();
    }

    public void clickOnMyAccount(){
        driver.findElement(By.cssSelector(".action.switch")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("My Account")));
        driver.findElement(By.linkText("My Account")).click();
    }

    public void clickOnMyWishList(){
        driver.findElement(By.cssSelector(".action.switch")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("My Wish List")));
        driver.findElement(By.partialLinkText("My Wish List")).click();
    }

    public void signOut(){
        driver.findElement(By.cssSelector(".action.switch")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Sign Out")));
        driver.findElement(By.partialLinkText("Sign Out")).click();
    }
}
/*



    public void addToCompare(){
        wait.until(ExpectedConditions.elementToBeClickable((By.linkText("Add to Compare"))));
        driver.findElement(By.linkText("Add to Compare")).click();
    }

    public void addToWishList(){
        wait.until(ExpectedConditions.elementToBeClickable((By.linkText("Add to Wish List"))));
        driver.findElement(By.linkText("Add to Wish List")).click();
    }

    public void addProductToCart(String sizeLabel, String colorLabel){
        List<WebElement> sizes = getAllAvailableSizes();
        if (!sizes.isEmpty()) {
            chooseSizeByIndex(sizeLabel);
        }

        List<WebElement> colors = selectALLColors();
        if (!colors.isEmpty()) {
            selectColorByLabel(colorLabel);
        }

        addToCart();
    }

 */