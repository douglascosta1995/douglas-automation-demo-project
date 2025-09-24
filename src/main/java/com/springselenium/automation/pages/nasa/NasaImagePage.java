package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

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

    public void click_image() throws InterruptedException {

        List<WebElement> list_images = driver.findElements(images);

        Random random = new Random();
        int randomNumber = random.nextInt(list_images.size());

        String byString = images.toString();
        String old_xpath = byString.substring(byString.indexOf(":") + 1).trim();
        String new_xpath = "("+old_xpath+")[" + randomNumber+"]";

        By selected_images = By.xpath(new_xpath);

        WebElement image_to_click = driver.findElement(selected_images);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image_to_click);
        Thread.sleep(3000);
        image_to_click.click();
    }

    public String getImageAltText(){
        System.out.println(driver.findElement(open_image).getAttribute("alt"));
        return driver.findElement(open_image).getAttribute("alt");
    }
}

