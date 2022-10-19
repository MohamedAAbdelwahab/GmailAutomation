import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class Assignment1Test {
    WebDriver driver;
    Utils util=new Utils();
    PrintStream out = System.out;
    SoftAssert softAssert = new SoftAssert();
    @AfterClass
    public void cleanUP()
    {
        driver.close(); //Close the browser window
    }
    @BeforeClass
    public void setup()
    {
       driver=util.setup();
       driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

        System.setOut(out);
    }
    @Test(priority = 1)
     public  void OpenGoogleAndSearch()  {
        driver.get("https://www.google.com/");
        try
        {
            driver.findElement(By.xpath("//*[text()='English']")).click();
        }
        catch(Exception e)
        {
            System.out.println("No Element Or the page is already in english language");
        }
        HomePage page=new HomePage(driver);
        page.enterSearchKeywordAndSearch();

    }
    @Test(priority = 2)
    public void RemoveKeywordAndSearch()
    {
        SearchPage searchPage=new SearchPage(driver);
        searchPage.clearSearchBar();
        searchPage.searchNewKeyword("Software Testing new");
        Assert.assertTrue(searchPage.AssertOnSearchResultsExistence()); //Assert that number of results exist on UI
        searchPage.NavigateToPage2(); //Scroll down and go to the next page
        softAssert.assertTrue(searchPage.AssertThatNumberOfSearchResultsIsEqual(),"Number of results on page 2 not equal on page 3"); //Validate if the number of results on page 2 is equal to page 3 or not
        util.scrollUntil(driver.findElement(By.xpath("//*[@id='bres']/div/div/div/div/div[5]")));
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='bres']/div/div/div/div/div[5]")).size()>0); //Validate there are different search suggestions displayed at the end of the page
    }

}
