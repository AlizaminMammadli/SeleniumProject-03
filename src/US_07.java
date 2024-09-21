import Utility.BaseDriver;
import Utility.MyFunc;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class US_07 extends BaseDriver {


    @Test
    public void TC_01(){
        driver.get("https://shopdemo.e-junkie.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement eCommerce = driver.findElement(By.cssSelector("[class='EJ-ShopLink']"));
        eCommerce.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='We help you sell downloads']")));

        WebElement logo = driver.findElement(By.xpath("//*[@id='top']/header/div/div/div[1]/a/img"));
        logo.click();

        wait.until(ExpectedConditions.urlToBe("https://www.e-junkie.com/"));
        String url = driver.getCurrentUrl();

        Assert.assertTrue("URL dogrulanamadi.",url.equals("https://www.e-junkie.com/"));


        driver.quit();

    }
}
