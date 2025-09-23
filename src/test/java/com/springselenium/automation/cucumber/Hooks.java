package com.springselenium.automation.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {

    @Autowired
    protected WebDriver driver;

    //@After
    //public void afterScenario() {
    //    driver.quit();
    //}
}
