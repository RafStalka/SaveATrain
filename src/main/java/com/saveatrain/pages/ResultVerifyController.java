package com.saveatrain.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ResultVerifyController {
    WebDriver driver;
    public ResultVerifyController(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver, this);
    }
}
