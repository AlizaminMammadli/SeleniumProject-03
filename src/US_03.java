import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class US_03 extends BaseDriver {

    @Test
    public void TC_01(){

        driver.get("https://shopdemo.e-junkie.com/");
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));


        WebElement eBook =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick=\"return EJProductClick('1595015')\"]")));
        eBook.click();

        wait.withTimeout(Duration.ofSeconds(5));

        WebElement iframe =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payDebit =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Visa')]")));
        payDebit.click();

        WebElement email =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email']")));
        email.sendKeys("random@gmail.com");

        WebElement rEmail =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Confirm Email']")));
        rEmail.sendKeys("random@gmail.com");

        WebElement nameCard =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name On Card']")));
        nameCard.sendKeys("Random Modnar");

        driver.switchTo().frame(1);
        WebElement cardNum =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='cardnumber']")));
        cardNum.sendKeys("1111111111111111");

        driver.switchTo().parentFrame();
        WebElement payBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Pay-Button']")));
        payBtn.click();

        WebElement msgFail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SnackBar']")));

        System.out.println(msgFail.getText());

        Assert.assertTrue("Message not Displayed", msgFail.isDisplayed());

        driver.quit();

    }
}
