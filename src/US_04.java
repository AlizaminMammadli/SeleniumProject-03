import Utility.BaseDriver;
import Utility.MyFunc;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class US_04 extends BaseDriver {

    @Test
    public void TC_01() throws AWTException {

        driver.get("https://shopdemo.fatfreeshop.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js=(JavascriptExecutor)driver;
        Actions actions=new Actions(driver);

        WebElement addToCart = driver.findElement(By.xpath("(//i[@class='ion-ios-cart cart_icon'])[2]"));
        actions.click(addToCart).build().perform();

        WebElement iframe1=driver.findElement(By.cssSelector("[class='EJIframeV3 EJOverlayV3']"));
        driver.switchTo().frame(iframe1);
        MyFunc.wait(2);
        WebElement creditcard = driver.findElement(By.xpath("//span[text()='Visa, AMEX, MasterCard, Maestro, Discover']"));
        js.executeScript("arguments[0].click();", creditcard);

        WebElement email = driver.findElement(By.xpath("//*[@placeholder='Email']"));
        email.sendKeys("test@gmail");

        WebElement confirmemail = driver.findElement(By.xpath("//input[@placeholder=\"Confirm Email\"]"));
        confirmemail.sendKeys("test@gmail");

        WebElement name = driver.findElement(By.xpath("//*[@placeholder='Name On Card']"));
        name.sendKeys("Jimmy Handers");

        MyFunc.wait(1);
        driver.switchTo().frame(1);
        WebElement cardnumber = driver.findElement(By.xpath("(//span[@class='InputContainer'])[1]"));
        actions.click(cardnumber).build().perform();
        actions.sendKeys("4242424242424242").build().perform();
        MyFunc.wait(1);

        WebElement aytarih = driver.findElement(By.xpath("(//div[@class='CardField-input-wrapper']//span)[8]"));
        actions.click(aytarih).build().perform();
        actions.sendKeys("12 24").build().perform();
        MyFunc.wait(1);

        WebElement cvc= driver.findElement(By.xpath("(//span[@class='InputContainer'])[3]"));
        actions.click(cvc).build().perform();
        actions.sendKeys("000").build().perform();
        MyFunc.wait(1);

        driver.switchTo().parentFrame();
        WebElement paybutton= driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        paybutton.click();

        driver.get("https://www.fatfreecartpro.com/ecom/rp.php?rdffc=true&txn_id=st-ch_3Q1UqPFWSmRjvnlt0K0iMWzL&payer_email=test%40gmail&client_id=341695&c_id=200902483&c_enc=623b459f72eda1b14bcdca84175df54a&cart_metadata=%7B%22gtag%22%3A%7B%22gtag%22%3A%22UA-273877-2%22%2C%22_ga%22%3A%222.46030244.1666141791.1726931003-1960235321.1726931003%22%7D%2C%22fbp%22%3A%7B%22fbp%22%3A%221714673711932838%22%7D%2C%22cart_source%22%3A%22https%3A%2F%2Fshopdemo.fatfreeshop.com%2F%22%2C%22em_updates%22%3Atrue%7D&firstLoad=true&&pending_reason=&_ga=2.46030244.1666141791.1726931003-1960235321.1726931003&&gajs=&auser=&abeacon=&");
        WebElement confirMsj= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"green_text_margin\"]")));
        Assert.assertTrue("Mesaj Görüntülenemedi",confirMsj.isDisplayed());
        System.out.println("Confirm Message = " + confirMsj.getText());
        tearDown();


    }
}
