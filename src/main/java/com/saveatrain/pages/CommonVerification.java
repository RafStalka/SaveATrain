package com.saveatrain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.Assert;

import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class CommonVerification {
    static final Logger log = getLogger(lookup().lookupClass());
    WebDriver driver;
    WebDriverWait wait;
    int timeoutSec = 5; // wait timeout
    public CommonVerification(WebDriver driver) {
        this.driver=driver;
        //PageFactory.initElements( driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));

    }

    private CommonVerification() {

    }

    public static CommonVerification getCommonVerification(WebDriver driver) {
        return new CommonVerification(driver);
    }

    public CommonVerification verifyIsOpened(By element) {
        Assert.assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }
    public void setTimeoutSec(int timeoutSec) {
        this.timeoutSec = timeoutSec;
    }
    public boolean isDisplayed(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            log.warn("Timeout of {} wait for {}", timeoutSec, locator);
            return false;
        }
        return true;
    }

    public static By searchDetailsIsDisplayed() {
        return By.xpath("//div[@class='search-details']");
    }
}
