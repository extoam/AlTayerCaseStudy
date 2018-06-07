import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver   ;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class TestBase {


    protected WebDriver driver;
    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(driver);
    protected static String homePageURL;

    private String browser;

    public TestBase(String browser) {
        this.browser = browser;
    }

    static private String gridUrl;
    static private String browsersUnderTest;

    static {
        gridUrl = System.getProperty("test.selenium.hub.url"/*, "http://localhost:4444/wd/hub"*/);
        browsersUnderTest = System.getProperty("test.selenium.browsers", "chrome");
        homePageURL = System.getProperty("test.selenium.homepage", "https://www.mamasandpapas.ae");
    }


    @Parameterized.Parameters
    public static Collection data() {

        if (gridUrl == null) {
            return Arrays.asList(new Object[][]{{"chrome"}});
        } else {
            return Arrays.asList(browsersUnderTest.split(","));
        }
    }

    @Before
    public void setUp() throws MalformedURLException {

        if (gridUrl == null) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        } else {

            DesiredCapabilities capabilities = new DesiredCapabilities(new DesiredCapabilities(browser, "", Platform.ANY));
            driver = new RemoteWebDriver(new URL(gridUrl), capabilities);

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        screenshotTestRule.setDriver(driver);
    }
/*
    @After
    public void teardown() {
       *//* driver.close();
        driver.quit();*//*
    }*/
}
