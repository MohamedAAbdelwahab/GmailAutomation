import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    HomePage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(css = ("input[title='Search']"))
    WebElement searchBar;

    void enterSearchKeywordAndSearch()
    {
        searchBar.sendKeys("software testing");
        searchBar.sendKeys(Keys.ENTER);
    }
}
