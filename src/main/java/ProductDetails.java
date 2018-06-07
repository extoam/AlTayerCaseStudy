import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetails {

    public static final String productTitle = "//div[@class='title-and-price-wrapper']/h1";
    public static final String productQuantity = "//input[@name='quantity']";
    public static final String productWish = "//div[@class='product-detail row']//i[@class='fa fa-heart-o']";
    public static final String productWishMessage = "//button[@class='btn btn-empty toggle-favorites']/span";
    public static final String productWishedMessage = "//button[@class='btn btn-empty toggle-favorites favorited']/span";
    public static final String productImage = "//div[@class='product-detail row']//img";
    public static final String prQuantityDecrease1 = "//a[@class='decrease disabled']";
    public static final String prQuantityIncrease = "//a[@class='increase ']/i";
    public static final String wishHeaderQuantity = "//a[@id='list-user-favorites']/em";
    public static final String removeFromFavorites = "//div[@class='product-detail row']//i[@class='fa fa-heart']";
    public static final String productImageActive = "//figure[@class='media_image slick-slide slick-current slick-active']/a";
    public static final String productImageSecond = "//figure[@class='media_image slick-slide slick-active' and @data-slick-index='2']/a";

    private static WebDriver driver;

    public ProductDetails(WebDriver driver) {
        this.driver = driver;

    }

    private final long TIMEOUT = 100;

    public void productDetailsIncreaseQuantity() throws InterruptedException{
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(productQuantity)));
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(prQuantityIncrease)));
        WebElement quantity = driver.findElement(By.xpath(prQuantityIncrease));
        Thread.sleep(5000);
        for(int i=0; i<10; i++){
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", quantity);
        }
    }

    public void clickFavoritesButton(){
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(productWish)));
        WebElement addToWishList = driver.findElement(By.xpath(productWish));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", addToWishList);

    }

    public void removeFromFavotires(){
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(removeFromFavorites)));
        WebElement removeFromWishList = driver.findElement(By.xpath(removeFromFavorites));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", removeFromWishList);

    }

    public String activeImage(){
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(productImageActive)));
        return driver.findElement(By.xpath(productImageActive)).getAttribute("href");
    }


    public String clickImage(){
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(productImageSecond)));
        WebElement image = driver.findElement(By.xpath(productImageSecond));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", image);
        return driver.findElement(By.xpath(productImageActive)).getAttribute("href");
    }
}

