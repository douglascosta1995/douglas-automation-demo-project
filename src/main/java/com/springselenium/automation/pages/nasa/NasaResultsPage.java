package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@LazyComponent
public class NasaResultsPage extends AbstractPage {

    private final By nasa_header = By.id("header-logo");

    private final By contentTypeCheckBox = By.xpath("//input[@name = 'content-type' and @value='Articles']");

    private final By applyFiltersBtn = By.xpath("//*[text()='Apply Filters']");
    //private final By applyFiltersBtn = By.xpath("//*[contains(@class, 'usa-button') and contains(@class, 'hds-button') and contains(@class, 'filter')]");
    //private final By applyFiltersBtn = By.cssSelector(".usa-button.hds-button.filter");
    private final By pageTitle = By.className("page-title");

    private final By contentType = By.xpath("//button[@class='content_typearticles dynamic-chip']/span[@class='filter-label']");

    public boolean isAt() {
        return wait.until((d) -> driver.findElement(nasa_header).isDisplayed());
    }

    public void filterByContentType() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(contentTypeCheckBox));
        driver.findElement(contentTypeCheckBox).click();
    }

    public void clickApplyFilter() throws InterruptedException {
        WebElement button = driver.findElement(applyFiltersBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        Thread.sleep(5000);
        button.click();
    }

    public String getResultsTitle(){
        return driver.findElement(pageTitle).getText();
    }

    public String getContentType(){
        return driver.findElement(contentType).getText();
    }
}
