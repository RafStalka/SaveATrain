package com.saveatrain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    public void fillFields(By selector, String value){
        String s = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(selector).sendKeys(s);
        driver.findElement(selector).sendKeys(value);
    }

    public void  fillFromAndToFields(String from, String to){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOfElementLocated(startStationName));
        fillFields(startStationName,from);
        wait.until(ExpectedConditions.visibilityOfElementLocated(routeListStart));
        driver.findElement(routeListStart).click();
        fillFields(endStationName,to);
        wait.until(ExpectedConditions.visibilityOfElementLocated(routeListEnd));
        driver.findElement(routeListEnd).click();
    }
    public void fillSpecificDepartureDate(String date){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOfElementLocated(departureTime));
        driver.findElement(departureTime).clear();
        driver.findElement(departureTime).sendKeys(date);

    }
    public void fillPassengerTypeAndNumber(String passengerType, String passengerNumber){
        Select drpPassType = new Select(driver.findElement(By.name("pass_type")));
        drpPassType.selectByVisibleText(passengerType);
        Select drpPassNumber = new Select(driver.findElement(By.name("pass_num")));
        drpPassNumber.selectByVisibleText(passengerNumber);
    }
    public SearchResultPage clickSearchButton(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(findButton));
        Assert.assertTrue(driver.findElement(findButton).isDisplayed());
        driver.findElement(findButton).click();
        return new SearchResultPage();
    }

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
