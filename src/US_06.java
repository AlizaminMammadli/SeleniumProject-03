import Utility.BaseDriver;
import Utility.MyFunc;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class US_06 extends BaseDriver {

    @Test
    public void TC_01() throws AWTException {
        driver.get("https://shopdemo.fatfreeshop.com/?");
        WebElement contactUs=driver.findElement(By.xpath("((//div[@class=\"container\"])[3]//following::div[2])[1]//following-sibling::a"));
        contactUs.click();
        MyFunc.wait(1);

        WebElement name=driver.findElement(By.id("sender_name"));
        name.sendKeys("Arif");
        MyFunc.wait(1);

        WebElement ePosta=driver.findElement(By.id("sender_email"));
        ePosta.sendKeys("test@test.com");
        MyFunc.wait(1);

        WebElement subject=driver.findElement(By.id("sender_subject"));
        subject.sendKeys("problem");
        MyFunc.wait(1);

        WebElement message=driver.findElement(By.id("sender_message"));
        message.sendKeys("E-book didn't open.");
        MyFunc.wait(1);

        WebElement sendButton=driver.findElement(By.id("send_message_button"));
        sendButton.click();
        MyFunc.wait(1);

        WebDriverWait bekle=new WebDriverWait(driver, Duration.ofSeconds(10));

        bekle.until(ExpectedConditions.alertIsPresent());
        String alertMessage=driver.switchTo().alert().getText();
        Assert.assertTrue("Message couldn't visiable",alertMessage.equals("Recaptcha didn't match"));
        MyFunc.wait(1);

        driver.switchTo().alert().accept();
        MyFunc.wait(1);


        tearDown();
    }
}
