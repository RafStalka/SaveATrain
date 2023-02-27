package com.saveatrain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class ResultActController {
    static final Logger log = getLogger(lookup().lookupClass());
    WebDriver driver;
    public ResultActController(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver, this);
    }

    public ResultActController verifyResultTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='search-details']")));
        String text = driver.getTitle();
        assertThat(text).contains(title);
        return this;
    }
}
