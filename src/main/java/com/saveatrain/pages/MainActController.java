package com.saveatrain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MainActController {
    WebDriver driver;
    public MainActController(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver, this);
    }
    By startStationInput = By.xpath("//input[@placeholder='From']");
    By endStationInput = By.xpath("//input[@placeholder='To']");
    By findButton = By.xpath("//button[@name='button']");

    public ResultActController simpleSearch(String value, String vale1) {
        driver.findElement(startStationInput).sendKeys(value);
        driver.findElement(endStationInput).sendKeys(vale1);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(findButton));
        Assert.assertTrue(driver.findElement(findButton).isDisplayed());
        driver.findElement(findButton).submit();
        return new ResultActController(driver);
    }
}
