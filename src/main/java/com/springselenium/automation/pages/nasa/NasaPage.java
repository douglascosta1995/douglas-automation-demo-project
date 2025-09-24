package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@LazyComponent
public class NasaPage extends AbstractPage {
    //@Value("${application.url}")
    private String url = "https://www.nasa.gov/";

    private final By nasa_header = By.id("header-logo");

    private final By searchBox = By.id("search-field-en-small--desktop");

    private final By button_explore = By.id("global-navigation-trigger");

    private final By button_link_technology = By.xpath("//li[@submenu-id='global-nav-technology']//span[text()='Technology']");

    private final By button_link_technology_submenu = By.xpath("//div[@submenu-id='global-nav-technology']//*[text()='Technology']");

    private final By button_multimedia = By.xpath("//button[./span[text()='Multimedia']]");

    private final By button_image_of_day_submenu = By.xpath("//ul[@id='news-galleries-submenu']//*[text()='Image of the Day']");

    private final By button_contact_nasa = By.xpath("//a/span[text()='Contact NASA']");

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

    public void clickExplore() {

        driver.findElement(button_explore).click();
    }

    public void clickTechnology() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(button_link_technology));
        driver.findElement(button_link_technology).click();
        wait.until(ExpectedConditions.elementToBeClickable(button_link_technology_submenu));
        driver.findElement(button_link_technology_submenu).click();
    }

    public void clickMultimedia(){
        driver.findElement(button_multimedia).click();
    }

    public void clickImageOfDay(){
        driver.findElement(button_image_of_day_submenu).click();
    }

    public void clickContactNasa() throws InterruptedException {
        WebElement button = driver.findElement(button_contact_nasa);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        Thread.sleep(5000);
        button.click();
    }

}
