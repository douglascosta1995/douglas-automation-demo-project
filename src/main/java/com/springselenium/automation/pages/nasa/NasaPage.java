package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class NasaPage extends AbstractPage {
    //@Value("${application.url}")
    private String url = "https://www.nasa.gov/";

    private final By nasa_header = By.id("header-logo");

    private final By searchBox = By.id("search-field-en-small--desktop");


    @Override
    public boolean isAt() {
        return wait.until((d) -> driver.findElement(nasa_header).isDisplayed());
    }

    public void goTo() {
        driver.get(url);
    }

    public void search(String search) {
        driver.findElement(searchBox).sendKeys(search);
    }

    public void clickSearch() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public void quit() {
        driver.quit();
    }
}
