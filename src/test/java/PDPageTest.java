import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PDPageTest extends TestBase {

    public static final String fbEmail1 = "ktwxjgb_sharpeman_1465123980@tfbnw.net";
    public static final String fbEmail2 = "maioiik_smithwitz_1465123955@tfbnw.net";
    public static final String fbEmail3 = "zointpr_thurnson_1465123943@tfbnw.net";
    public static final String fbEmail4 = "open_reyhddn_user@tfbnw.net";
    public static final String fbPassword = "1234qwe";

    public PDPageTest(String browser) {
        super(browser);
    }

    private final long TIMEOUT = 50;


    @Test
    public  void openProductDetailsTest() throws InterruptedException{
        String searchQuery = "Red Fox - Activity Toy";
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.searchItemsSubmit(searchQuery);
        home.openProductDetails(searchQuery);

        ProductDetails productDetails = new ProductDetails(driver);

        Assert.assertEquals(driver.findElement(By.xpath(productDetails.productTitle)).getText(), searchQuery);
        Assert.assertTrue(driver.findElements(By.xpath(productDetails.productQuantity)).size() != 0);
        Assert.assertTrue(driver.findElements(By.xpath(productDetails.productWish)).size() != 0);
        Assert.assertTrue(driver.findElements(By.xpath(productDetails.productImage)).size() != 0);
    }

    @Test
    public  void changeProductQuantity() throws InterruptedException{
        //failed test - needs screenshot in test report
        String searchQuery = "Red Fox - Activity Toy";
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.searchItemsSubmit(searchQuery);
        home.openProductDetails(searchQuery);

        ProductDetails productDetails = new ProductDetails(driver);

        Assert.assertEquals(driver.findElement(By.xpath(productDetails.productTitle)).getText(), searchQuery);
        Assert.assertTrue(driver.findElements(By.xpath(productDetails.prQuantityDecrease1)).size() > 0);

        productDetails.productDetailsIncreaseQuantity();
        String quantityValue = driver.findElement(By.xpath(productDetails.productQuantity)).getAttribute("value");
        Assert.assertEquals(10, quantityValue);
    }

    @Test
    public  void wishlistNotLoggedUser() throws InterruptedException{
        String searchQuery = "Red Fox - Activity Toy";
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.searchItemsSubmit(searchQuery);
        home.openProductDetails(searchQuery);

        ProductDetails productDetails = new ProductDetails(driver);

        Assert.assertEquals(driver.findElement(By.xpath(productDetails.productWishMessage)).getText(), "ADD TO WISHLIST");
        productDetails.clickFavoritesButton();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElements(By.xpath(home.signInModal)).size() > 0);

    }

    @Test
    public void wishListLoggedUser() throws InterruptedException{
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.clickSignIn();


        home.facebookLogin(fbEmail3, fbPassword);
        home.facebookSuccessfulLogin();

        String searchQuery = "Urbo2 Pushchair - City Grey";

        home.searchItemsSubmit(searchQuery);
        home.openProductDetails(searchQuery);

        ProductDetails productDetails = new ProductDetails(driver);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(productDetails.productWishMessage)));
        Assert.assertEquals(driver.findElement(By.xpath(productDetails.productWishMessage)).getText(), "ADD TO WISHLIST");
        productDetails.clickFavoritesButton();
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(productDetails.productWishedMessage)));
        Assert.assertEquals(driver.findElement(By.xpath(productDetails.productWishedMessage)).getText(), "REMOVE FROM WISHLIST");
        Assert.assertEquals(driver.findElement(By.xpath(productDetails.wishHeaderQuantity)).getText(), "1");
        productDetails.removeFromFavotires();
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(productDetails.productWishMessage)));
        Assert.assertEquals(driver.findElement(By.xpath(productDetails.productWishMessage)).getText(), "ADD TO WISHLIST");
        Assert.assertEquals(driver.findElement(By.xpath(productDetails.wishHeaderQuantity)).getText(), "0");
        home.logout();
    }

    @Test
    public void productSeveralImages() throws InterruptedException{
        String searchQuery = "Urbo2 Pushchair - City Grey";
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.searchItemsSubmit(searchQuery);
        home.openProductDetails(searchQuery);

        ProductDetails productDetails = new ProductDetails(driver);

        Assert.assertTrue(driver.findElements(By.xpath(productDetails.productImage)).size() > 0);
        String image1 = productDetails.activeImage();
        String image2 = productDetails.clickImage();
        Assert.assertNotEquals(image1, image2);

    }

}
