import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.File;
import java.io.FileOutputStream;

class ScreenshotTestRule implements MethodRule {
    public ScreenshotTestRule( WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    captureScreenshot(frameworkMethod.getName());
                    throw t;
                }finally {
                    driver.close();
                    driver.quit();
                }
            }

            public void captureScreenshot(String fileName) {
                try {
                    new File("target/surefire-reports/").mkdirs();
                    String filePath = "target/surefire-reports/screenshot-" + fileName ;
                    if(driver instanceof RemoteWebDriver){
                        filePath = filePath + "-" + ((RemoteWebDriver)driver).getCapabilities().getBrowserName();
                    }
                    filePath = filePath + ".png";

                    FileOutputStream out = new FileOutputStream(filePath);
                    System.out.println("[[ATTACHMENT|" +  System.getProperty("user.dir") + "/" + filePath + "]]");
                    WebDriver underlyingDriver = driver;

                    WebDriver augmentedDriver = new Augmenter().augment(underlyingDriver);

                    out.write(((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES));
                    out.close();
                } catch (Throwable e) {
                    System.out.println(e.getMessage());
                }
            }
        };
    }
}
