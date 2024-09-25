import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class US_02 extends BaseDriver {

    public static WebDriverWait wait;

    @Test
    public void TC_302() {
        driver.get("https://shopdemo.fatfreeshop.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement addCart = driver.findElement(By.xpath("//button[contains(@onclick, '1595015')]"));
        addCart.click();

        WebElement iframeCart = driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        driver.switchTo().frame(iframeCart);

        WebElement productCount = driver.findElement(By.xpath("//div[@class='Fixed-Actions Desktop-Only']//span[@class='Cart-Items-Nos']"));
        wait.until(ExpectedConditions.visibilityOf(productCount));
        String number = productCount.getText();
        Assert.assertTrue("Sepet boş durumda.", !number.equals("0"));

        WebElement bankCredCartButton = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        wait.until(ExpectedConditions.elementToBeClickable(bankCredCartButton));
        bankCredCartButton.click();

        WebElement payButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton.click();

        WebElement warning1 = driver.findElement(By.xpath("//span[text()='Invalid Email']"));
        WebElement warning2 = driver.findElement(By.xpath("//span[text()='Invalid Billing Name']"));

        wait.until(ExpectedConditions.visibilityOf(warning1));

        String warning1Text = warning1.getText();
        String warning2Text = warning2.getText();

        Assert.assertTrue("Mail hatası verilemedi.", warning1Text.contains("Invalid Email"));
        Assert.assertTrue("Fatura ismi hatası verilemedi.", warning2Text.contains("Invalid Billing Name"));

        tearDown();
    }
}
