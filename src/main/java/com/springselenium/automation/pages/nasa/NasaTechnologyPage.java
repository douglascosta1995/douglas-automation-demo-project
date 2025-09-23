package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@LazyComponent
public class NasaTechnologyPage extends AbstractPage {

    private final By nasa_header = By.id("header-logo");

    private final By article_tag = By.xpath("//*[text()='Article']");

    @Override
    public boolean isAt() {
        return wait.until((d) -> driver.findElement(nasa_header).isDisplayed());
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public int getArticleNumber(){
        List<WebElement> articles = driver.findElements(article_tag);
        return articles.size();
    }
}
