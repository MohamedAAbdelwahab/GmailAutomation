import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;
    public SearchPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="result-stats")
    WebElement searchResults;
    @FindBy(css="a[aria-label='Page 2']")
    WebElement page2;
    @FindBy(css="a[aria-label='Page 3']")
    WebElement page3;
    @FindBy(css="input[value='software testing']")
    WebElement searchBar;
    public boolean AssertOnSearchResultsExistence()
    {

        if(!searchResults.getText().isEmpty())
        {
            String str=searchResults.getText();
            String numbers=str.substring(6,str.indexOf("results"));
            System.out.println(numbers);
            return true;
        }

    return false;
    }
    public void NavigateToPage2()
    {
        Scroll(page2);
        page2.click();
    }
    public void NavigateToPage3()
    {
        Scroll(page3);
        page3.click();
    }
    public void Scroll(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);

    }
    public boolean AssertThatNumberOfSearchResultsIsEqual()
    {
        searchResults=driver.findElement(By.id("result-stats"));
        String searchResultsTextInPage2=searchResults.getText();
        String numbers2=searchResultsTextInPage2.substring(16,searchResultsTextInPage2.indexOf("results"));
        System.out.println(numbers2);
        Scroll(page3);
        NavigateToPage3();
        searchResults=driver.findElement(By.id("result-stats"));
        String searchResultsTextInPage3=searchResults.getText();
        String numbers3=searchResultsTextInPage3.substring(16,searchResultsTextInPage3.indexOf("results"));
        System.out.println(numbers3);
        return numbers2.equals(numbers3);
    }
    void clearSearchBar()
    {
        searchBar.clear();
    }
    void searchNewKeyword(String str)
    {
        searchBar.sendKeys(str);
        searchBar.sendKeys(Keys.ENTER);

    }
}
