package com.saveatrain;

import com.saveatrain.config.Configuration;
import com.saveatrain.driver.DriverFactory;
import com.saveatrain.pages.CommonVerification;
import com.saveatrain.pages.MainPage;
import com.saveatrain.pages.ResultPage;
import io.qameta.allure.Description;
import org.testng.Reporter;
import org.testng.annotations.*;

import static com.saveatrain.config.ConfigurationManager.configuration;
import static com.saveatrain.pages.CommonVerification.*;
import static com.saveatrain.pages.MainPage.getMainPage;
import static com.saveatrain.pages.ResultPage.getResultPage;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

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
    public void tearDown() {
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
