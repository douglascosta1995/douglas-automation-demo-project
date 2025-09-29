package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

@LazyComponent
public class NasaImagePage extends AbstractPage {

    private final By nasa_header = By.id("header-logo");

    private final By images = By.xpath("//div[@class='hds-gallery-items']//img");

    private final By open_image = By.xpath("//img[contains(@class, 'attachment')]");

    @Override
    public boolean isAt() {
        return wait.until((d) -> driver.findElement(nasa_header).isDisplayed());
    }

    public void click_image() {
        List<WebElement> list_images = driver.findElements(images);

        Random random = new Random();
        int randomNumber = random.nextInt(list_images.size());

        String byString = images.toString();
        String old_xpath = byString.substring(byString.indexOf(":") + 1).trim();
        String new_xpath = "("+old_xpath+")[" + randomNumber+"]";

        By selected_images = By.xpath(new_xpath);

        scroll_into_view_and_click(selected_images);
    }

    public String getImageAltText(){
        return driver.findElement(open_image).getAttribute("alt");
    }

    /// Helper Functions ///

    public void scroll_into_view_and_click(By button_to_be_clicked){
        WebElement button = driver.findElement(button_to_be_clicked);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", button);

        wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "var elem = arguments[0], box = elem.getBoundingClientRect();" +
                                "return (box.top >= 0 && box.bottom <= window.innerHeight);", button));

        wait.until(ExpectedConditions.visibilityOf(button));
        wait.until(ExpectedConditions.elementToBeClickable(button));

        button.click();
    }
}

