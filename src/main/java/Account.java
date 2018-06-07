import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Account {

    public static final String accountTitle = "//a[@title='HomePage']";
    public static final String userEmailField = "//div[@class='col-md-6 contact-information']/p[2]";

    private static WebDriver driver;

    public Account(WebDriver driver) {
        this.driver = driver;

    }

    private final long TIMEOUT = 100;

    public String getEmailContact(){
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(userEmailField)));
        String emailContact = driver.findElement(By.xpath(userEmailField)).getText();
        return emailContact;
    }


}
