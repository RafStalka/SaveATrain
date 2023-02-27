package com.saveatrain.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ResultGetController {
    WebDriver driver;
    public ResultGetController(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver, this);
    }
}
