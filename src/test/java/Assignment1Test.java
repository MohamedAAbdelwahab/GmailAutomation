import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Assignment1Test {
    WebDriver driver;
    Utils util=new Utils();
    PrintStream out = System.out;

    @BeforeClass
    public void setup()
    {
       driver=util.setup();
       driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

        System.setOut(out);
    }
    @Test(priority = 1)
     public  void LoginPage()  {
        driver.get("https://mail.google.com/mail/");
        WebElement email=driver.findElement(By.id("identifierId"));
        email.sendKeys("AutomationTesting12344@gmail.com");
        WebElement nextBTN=driver.findElement(By.id("identifierNext"));
        nextBTN.click();
        util.waitUntil(driver,By.id("password"));
        WebElement password=driver.findElement(By.cssSelector("input[class=\"whsOnd zHQkBf\"]"));
        password.sendKeys("P@ssw0rd1234");

        util.waitUntil(driver,By.id("passwordNext"));
        WebElement passwordNext=driver.findElement(By.id("passwordNext"));
        passwordNext.click();
    }
    @Test(priority = 2)
    public void MainEmailPage()
    {
        util.waitUntil(driver,By.cssSelector(".aic .z0 div"));
        WebElement composeBTN=driver.findElement(By.cssSelector(".aic .z0 div"));
        composeBTN.click();
    }

    @Test(priority = 3)
    public void ComposePage()
    {
        util.waitUntil(driver,By.cssSelector(".oj div textarea"));
        WebElement tofield=driver.findElement(By.cssSelector(".oj div textarea"));
        tofield.sendKeys("AutomationTesting12344@gmail.com");
        util.waitUntil(driver,By.cssSelector(".aoD.az6 input"));
        WebElement subjectfield=driver.findElement(By.cssSelector(".aoD.az6 input"));
        subjectfield.sendKeys("Test Mail");
        util.waitUntil(driver,By.cssSelector(".Ap .Ar.Au"));
        WebElement emailBody=driver.findElement(By.cssSelector(".Ap .Ar.Au div"));
        emailBody.sendKeys("Test Email Body");
        WebElement send=driver.findElement(By.cssSelector(".gU.Up .J-J5-Ji.btA .dC div"));
        send.click();
        util.waitUntil(driver,By.cssSelector(".zA.zE .apU.xY"));
        WebElement star=driver.findElement(By.cssSelector(".zA.zE .apU.xY"));
        star.click();
    }
    @Test(priority = 4)
    public void OpenEmail()
    {
        util.waitUntil(driver,By.cssSelector(".Cp div table tbody tr"));
        WebElement email=driver.findElement(By.cssSelector(".Cp div table tbody tr"));
        email.click();
        WebElement body=driver.findElement(By.xpath("//*[contains(text(), 'Test Email Body')]"));
        String bodystr =body.getText();

        Assert.assertEquals(bodystr.substring(bodystr.indexOf("Test")),"Test Email Body");
    }

}
