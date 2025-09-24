package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

@LazyComponent
public class NasaContactPage extends AbstractPage {

    private final By nasa_header = By.id("header-logo");

    private final By button_contact_nasa = By.xpath("//a[text()='Contact NASA.']");

    @Override
    public boolean isAt() {
        return wait.until((d) -> driver.findElement(nasa_header).isDisplayed());
    }

    public void clickContactNasa() throws InterruptedException {
        WebElement button = driver.findElement(button_contact_nasa);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        Thread.sleep(5000);
        button.click();
        Thread.sleep(5000);
    }

}
