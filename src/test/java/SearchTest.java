import org.junit.Assert;
import org.junit.Test;

public class SearchTest extends TestBase {

    public SearchTest(String browser) {
        super(browser);
    }
   @Test
    public  void searchResults() throws InterruptedException {
        String searchQuery = "red";
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.searchItemsSubmit(searchQuery);
        int itemsCount = home.searchResult();
        Assert.assertEquals(12, itemsCount); // failed step
        home.searchScroll();

    }


    @Test
    public  void searchNoResult() throws InterruptedException {
        String searchQuery = "noitemswiththiskeyword";
        driver.get(homePageURL);
        Home home = new Home(driver);
        home.openHomePage();
        home.searchItemsSubmit(searchQuery);
        String searchResponse = home.searchUnsuccessful();
        Assert.assertEquals(searchResponse, "You searched for " + searchQuery+", we're sorry we couldn't find anything to match your search.");
    }

   

}
