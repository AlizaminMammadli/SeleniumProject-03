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

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        wait.until(ExpectedConditions.visibilityOf(email));
        email.clear();
        WebElement confirmEmail = driver.findElement(By.xpath("//input[@placeholder='Confirm Email']"));
        confirmEmail.clear();
        WebElement name = driver.findElement(By.xpath("       //input[@placeholder='Name On Card']"));
        name.clear();
        WebElement phone = driver.findElement(By.xpath("//p[@class='Billing-Phone Inline']//input[@autocomplete='phone']"));
        phone.clear();
        WebElement company = driver.findElement(By.xpath("//p[@class='Billing-Company']//input[@autocomplete='company']"));
        company.clear();
        WebElement notes = driver.findElement(By.xpath("//p[@class='BuyerNotes']//textarea"));
        notes.clear();

        WebElement iframeSecuredCardInfo = driver.findElement(By.xpath("//iframe[contains(@name, '__privateStripeFrame')]"));

        driver.switchTo().frame(iframeSecuredCardInfo);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Card number']")));
        WebElement cardNumber = driver.findElement(By.xpath("//input[@placeholder='Card number']"));
        cardNumber.clear();

        WebElement cardExpiration = driver.findElement(By.xpath("//input[@placeholder='MM / YY' and @inputmode='numeric']"));
        cardExpiration.clear();

        WebElement cardCVC = driver.findElement(By.xpath("//input[@placeholder='CVC']"));
        cardCVC.clear();

        driver.switchTo().parentFrame();

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
