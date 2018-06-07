import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class FacebookLoginTest extends TestBase
{

    public static final String fbEmail1 = "ktwxjgb_sharpeman_1465123980@tfbnw.net";
    public static final String fbEmail2 = "maioiik_smithwitz_1465123955@tfbnw.net";
    public static final String fbEmail3 = "zointpr_thurnson_1465123943@tfbnw.net";
    public static final String fbEmail4 = "open_reyhddn_user@tfbnw.net";
    public static final String fbPassword = "1234qwe";

    public FacebookLoginTest(String browser) {
        super(browser);
    }
    
    
     @Test
    public  void facebookLoginCancel() throws InterruptedException{
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.clickSignIn();
        home.facebookLoginCancel();
        Assert.assertEquals(driver.findElement(By.xpath(home.signInButton)).getText(), "Sign In / Register");

    }

    @Test
    public  void facebookLoginSuccessful() throws InterruptedException {
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.clickSignIn();


        home.facebookLogin(fbEmail1, fbPassword);
        home.facebookSuccessfulLogin();

        home.openAccount();

        Account account = new Account(driver);
        String emailContact = account.getEmailContact();
        Assert.assertEquals(emailContact, fbEmail1);

        home.logout();

    }

   


}
