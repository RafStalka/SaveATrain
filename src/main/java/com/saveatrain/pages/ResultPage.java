package com.saveatrain.pages;

import org.openqa.selenium.WebDriver;

public class ResultPage {
    private ResultActController act;
    private ResultGetController get;
    private ResultVerifyController verify;
    public ResultActController act() {
        return act;
    }
    public ResultGetController get() {
        return get;
    }
    public ResultVerifyController verify() {
        return verify;
    }
    private ResultPage(ResultActController act, ResultGetController get,
                       ResultVerifyController verify) {
        this.act = act;
        this.get = get;
        this.verify = verify;
    }

    private ResultPage(WebDriver driver) {
        //hide it
    }
    public static ResultPage getResultPage(WebDriver driver) {
        return new ResultPage(new ResultActController(driver), new ResultGetController(driver),
                new ResultVerifyController(driver));
    }
}
