import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Home {

    public static final String signInButton = "//a[@class='login-button']";
    public static final String searchField = "//div[@class='input-field']/input";
    public static final String searchButton = "//button[@type='submit' and @value='Search']";
    public static final String signInModal = "//div[@class='container-fluid modal  in']";
    public static final String facebookButton = "//a[@class='btn btn-facebook loginWithFacebook']";
    public static final String fbEmail = "//input[@type='text' and @id='email']";
    public static final String fbPass = "//input[@type='password' and @id='pass']";
    public static final String fbLogin = "//button[@id='loginbutton']";
    public static final String fbLoginCancel = "//a[@target='_self']";

    public static final String userBox = "//div[@id='user-info-box' and @class='box-menu menu-to-right']/span";
    public static final String userBoxId = "user-info-box";

    public static final String popup = "//div[@class='onesignal-popover-container onesignal-reset slide-down close-popover']";
    public static final String popupNo = "//div[@id='onesignal-popover-dialog']//button[@class='align-right secondary popover-button']";

    public static final String accountLink = "//div[@id='user-info-box' and @class='box-menu menu-to-right']//a[@href='/customer' and contains(text(), 'Account')]";
    public static final String accountTitle = "//a[@title='HomePage']";
    public static final String logoutLink = "//a[@id='logout-link']";

    public static final String unsuccessfulSearch = "//p[@class='blank-search-message']";

    public static final String searchCollection = "//div[@class='row product-collection']";
    public static final String searchCollectionItems = "//div[@class='row product-collection']/div";
    public static final String searchViewMoreButton = "//a[@class='btn btn-empty load-more']";
    public static final String itemOne = "//a[@class='product-item'][strong[@title='%s']]";


    private static WebDriver driver;

    public Home(WebDriver driver) {
        this.driver = driver;

    }

    public void openHomePage() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement noThanks = wait.until(ExpectedConditions.elementToBeClickable(By.id("onesignal-popover-cancel-button")));

        noThanks.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("onesignal-popover-container")));

        WebElement register = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In / Register")));

        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        System.out.println(browserName);

        if ("chrome".compareToIgnoreCase(browserName) == 0) {
            Actions actions = new Actions(driver);
            actions.moveToElement(register);

            actions.sendKeys(Keys.RETURN);
            actions.build().perform();
        } else {
            register.click();

        }

    }


    public void clickSignIn() {

        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(signInButton)));
        WebElement signIn = driver.findElement(By.xpath(signInButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signIn);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(signInModal)));
    }


    //private final long FBTIMEOUT = 40;
    private final long TIMEOUT = 100;

    public void facebookLogin(String email, String password) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(facebookButton)));
        WebElement fbButton = driver.findElement(By.xpath(facebookButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fbButton);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(fbEmail)));
        WebElement fbemail = driver.findElement(By.xpath(fbEmail));
        fbemail.sendKeys(email);
        WebElement fbpass = driver.findElement(By.xpath(fbPass));
        fbpass.sendKeys(password);
        WebElement fbLoginButton = driver.findElement(By.xpath(fbLogin));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fbLoginButton);
    }

    public void facebookLoginCancel() {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(facebookButton)));
        WebElement fbButton = driver.findElement(By.xpath(facebookButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fbButton);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(fbEmail)));
        WebElement fbLoginCancelButton = driver.findElement(By.xpath(fbLoginCancel));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fbLoginCancelButton);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(signInButton)));
    }


    public void facebookSuccessfulLogin() {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.id(userBoxId)));
    }

    public void openAccount() {
        System.out.println("driver=" + driver);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(userBox)));
        WebElement userBoxOpen = driver.findElement(By.xpath(userBox));

        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        System.out.println(browserName);

        if ("chrome".compareToIgnoreCase(browserName) == 0) {
            userBoxOpen.click();
        }
        else{
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userBoxOpen);
        }


        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", userBoxOpen);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(accountLink)));
        WebElement account = driver.findElement(By.xpath(accountLink));
        if ("chrome".compareToIgnoreCase(browserName) == 0) {
            account.click();
        }
        else{
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userBoxOpen);
        }

        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", account);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(accountTitle)));
    }

    public void logout() {
        WebElement userBoxOpen = driver.findElement(By.xpath(userBox));

        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        System.out.println(browserName);

        if ("chrome".compareToIgnoreCase(browserName) == 0) {
            userBoxOpen.click();
        }
        else{
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userBoxOpen);
        }
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", userBoxOpen);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(logoutLink)));
        WebElement logout = driver.findElement(By.xpath(logoutLink));
        if ("chrome".compareToIgnoreCase(browserName) == 0) {
            logout.click();
        }
        else{
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);
        }
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath(signInButton)));

    }

    public void searchItemsSubmit(String searchQuery) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchField)));
        WebElement searchFields = driver.findElement(By.xpath(searchField));
        searchFields.sendKeys(searchQuery);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + searchQuery + "')", searchFields);
        WebElement clickSearchButton = driver.findElement(By.xpath(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickSearchButton);
    }

    public String searchUnsuccessful() {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(unsuccessfulSearch)));
        return driver.findElement(By.xpath(unsuccessfulSearch)).getText();
    }

    public int searchResult() {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchCollection)));
        List<WebElement> xpath = driver.findElements(By.xpath(searchCollectionItems));
        int xpathCount = xpath.size();
        System.out.println("Total xpath: " + xpathCount);
        return xpathCount;
    }

    public void searchScroll() {
        ((JavascriptExecutor) driver).executeScript("scroll(0,800)");
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchViewMoreButton)));
    }

    public void openProductDetails(String searchQuery) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchCollection)));
        String fullXpath = String.format(itemOne, searchQuery);
        WebElement item = driver.findElement(By.xpath(fullXpath));
        //new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(String.valueOf(item)));
        WebElement itemClick = driver.findElement(By.xpath(fullXpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", itemClick);

    }

}
