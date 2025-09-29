package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@LazyComponent
public class NasaResultsPage extends AbstractPage {

    private final By nasa_header = By.id("header-logo");

    private final By contentTypeCheckBox = By.xpath("//input[@name = 'content-type' and @value='Articles']");

    private final By applyFiltersBtn = By.xpath("//*[text()='Apply Filters']");

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

    public void clickApplyFilter() {
        scroll_into_view_and_click(applyFiltersBtn);
    }

    public String getResultsTitle(){
        return driver.findElement(pageTitle).getText();
    }

    public String getContentType(){
        return driver.findElement(contentType).getText();
    }

    /// Helper Functions ///

    public void scroll_into_view_and_click(By button_to_be_clicked){
        WebElement button = driver.findElement(button_to_be_clicked);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", button);

        wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "var elem = arguments[0], box = elem.getBoundingClientRect();" +
                                "return (box.top >= 0 && box.bottom <= window.innerHeight);", button));

        wait.until(ExpectedConditions.elementToBeClickable(button));
        wait.until(ExpectedConditions.visibilityOf(button));

        button.click();
    }
}
