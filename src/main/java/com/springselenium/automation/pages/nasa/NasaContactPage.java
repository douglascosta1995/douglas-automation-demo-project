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
public class NasaContactPage extends AbstractPage {

    private final By nasa_header = By.id("header-logo");

    private final By button_contact_nasa = By.xpath("//a[text()='Contact NASA.']");

    @Override
    public boolean isAt() {
        return wait.until((d) -> driver.findElement(nasa_header).isDisplayed());
    }

    public void clickContactNasa() {
        wait_page_to_load();
        scroll_into_view_and_click(button_contact_nasa);
    }

    /// Helper Functions ///

    public void scroll_into_view_and_click(By button_to_be_clicked){
        WebElement button = driver.findElement(button_to_be_clicked);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", button);

        wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "var elem = arguments[0], box = elem.getBoundingClientRect();" +
                                "return (box.top >= 0 && box.bottom <= window.innerHeight);", button));

        wait.until(ExpectedConditions.elementToBeClickable(button));
        wait.until(ExpectedConditions.visibilityOf(button));

        button.click();
    }

    public void wait_page_to_load(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
