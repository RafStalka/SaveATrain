package com.saveatrain;

import com.saveatrain.config.Configuration;
import com.saveatrain.driver.DriverFactory;
import com.saveatrain.pages.CommonVerification;
import com.saveatrain.pages.MainPage;
import com.saveatrain.pages.ResultPage;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import static com.saveatrain.config.ConfigurationManager.configuration;
import static com.saveatrain.pages.CommonVerification.*;
import static com.saveatrain.pages.MainPage.getMainPage;
import static com.saveatrain.pages.ResultPage.getResultPage;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainPageTest {
    protected WebDriver driver;
    protected Configuration configuration;
    MainPage mainPage;
    ResultPage resultPage;
    CommonVerification commonVerification;

    @BeforeMethod
    public void setUp() {
        Reporter.log("=====Browser Session Started=====", true);
        configuration = configuration();
        driver = new DriverFactory().createInstance(configuration().browser());
        driver.navigate().to(configuration().url());
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        Reporter.log("=====Application Started=====", true);

        mainPage = getMainPage(driver);
        resultPage = getResultPage(driver);
        commonVerification = getCommonVerification(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if(ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            File destination = new File(System.getProperty("user.dir") +
                    "/resources/screenshots/" +
                    testResult.getName() + " ==> " + formatter.format(timeNow) + ".png");

            try {
                FileHandler.copy(source, destination);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        driver.quit();
    }

    @Description("Test should search connection.")
    @Test
    public void shouldSearchConnection() {
        mainPage.act()
                .simpleSearch("Berlin", "Dresden");
        resultPage.act()
                .verifyResultTitle("Search - Save A Train");
        commonVerification
                .verifyIsOpened(searchDetailsIsDisplayed());
    }
    @Description("Test should search connection.")
    @Test
    public void shouldConnection() {
        mainPage.act()
                .simpleSearch("Berlin", "Dresden");
        resultPage.act()
                .verifyResultTitle("Search - Save A Train");
        commonVerification
                .verifyIsOpened(searchDetailsIsDisplayed());
    }
}
