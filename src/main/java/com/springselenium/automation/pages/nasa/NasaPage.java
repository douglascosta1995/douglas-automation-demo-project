package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    private final By multimedia_links = By.xpath("//ul[@id='news-galleries-submenu']//li/a");

    private final By nasa_facebook = By.xpath("//*[@title='NASA on Facebook']");

    private final By nasa_instagram = By.xpath("//*[@title='NASA on Instagram']");

    private final By nasa_x = By.xpath("//*[@title='NASA on X']");

    private final By nasa_youtube = By.xpath("//*[@title='NASA on YouTube']");


    public static boolean errorLoadingPage = false;
    public static boolean errorSocialMediaLinks = false;


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

    public void clickEachMultimediaLink() throws InterruptedException {
        clickMultimedia();
        List<WebElement> list_links = driver.findElements(multimedia_links);

        for(int i = 1; i < list_links.size()+1; i++ ) {
            String byString = multimedia_links.toString();
            String old_xpath = byString.substring(byString.indexOf(":") + 1).trim();
            String new_xpath = "("+old_xpath+")[" + i+"]";

            By link_to_be_clicked = By.xpath(new_xpath);
            clickLinkAndReturn(link_to_be_clicked);

        }
    }

    public void clickLinkAndReturn(By link) {
        driver.findElement(link).click();
        String pageSource = driver.getPageSource();
        assert pageSource != null;
        if(pageSource.contains("error-404")){
            System.out.println("An error occurred.");
            errorLoadingPage = true;
        };
        goTo();
        clickMultimedia();
    }

    public boolean validateAnyErrorPageLoad(){
        return errorLoadingPage;
    }

    public void clickSocialMediaLinks() throws InterruptedException {
        boolean successFacebookLink = clickFacebookValidateLink(nasa_facebook);
        boolean successInstagramLink = clickInstagramValidateLink(nasa_instagram);
        boolean successXLink = clickXValidateLink(nasa_x);
        boolean successYoutubeLink = clickYoutubeValidateLink(nasa_youtube);

        if(!successFacebookLink || !successInstagramLink || !successXLink || !successYoutubeLink){
            errorSocialMediaLinks = true;
        }
    }

    public boolean validateAnyErrorSocialMediaLinks(){
        return errorSocialMediaLinks;
    }

    public boolean clickFacebookValidateLink(By facebook_link) throws InterruptedException {
        WebElement button = driver.findElement(facebook_link);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        String originalWindow = driver.getWindowHandle();
        Thread.sleep(5000);
        button.click();
        Thread.sleep(5000);

        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String first = iterator.next(); // First handle is usually the original window
        String second = iterator.next(); // Second handle is the newly opened tab
        String new_tab = iterator.next();
        // Switch to the new tab
        driver.switchTo().window(new_tab);

        String newTabTitle = driver.getTitle();
        System.out.println("Title of the new tab: " + newTabTitle);
        driver.close();
        driver.switchTo().window(originalWindow);

        assert newTabTitle != null;
        return newTabTitle.contains("Facebook");
    }

    public boolean clickInstagramValidateLink(By instagram_link) throws InterruptedException {
        WebElement button = driver.findElement(instagram_link);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        String originalWindow = driver.getWindowHandle();
        Thread.sleep(5000);
        button.click();
        Thread.sleep(5000);

        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String first = iterator.next(); // First handle is usually the original window
        String second = iterator.next(); // Second handle is the newly opened tab
        String new_tab = iterator.next();
        // Switch to the new tab
        driver.switchTo().window(new_tab);

        String newTabTitle = driver.getTitle();
        System.out.println("Title of the new tab: " + newTabTitle);
        driver.close();
        driver.switchTo().window(originalWindow);

        assert newTabTitle != null;
        return newTabTitle.contains("Instagram");
    }

    public boolean clickXValidateLink(By x_link) throws InterruptedException {
        WebElement button = driver.findElement(x_link);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        String originalWindow = driver.getWindowHandle();
        Thread.sleep(5000);
        button.click();
        Thread.sleep(5000);

        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String first = iterator.next(); // First handle is usually the original window
        String second = iterator.next(); // Second handle is the newly opened tab
        String new_tab = iterator.next();
        // Switch to the new tab
        driver.switchTo().window(new_tab);

        String newTabTitle = driver.getTitle();
        System.out.println("Title of the new tab: " + newTabTitle);
        driver.close();
        driver.switchTo().window(originalWindow);

        assert newTabTitle != null;
        return newTabTitle.contains("(@NASA) / X");
    }

    public boolean clickYoutubeValidateLink(By youtube_link) throws InterruptedException {
        WebElement button = driver.findElement(youtube_link);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        String originalWindow = driver.getWindowHandle();
        Thread.sleep(5000);
        button.click();
        Thread.sleep(5000);
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String first = iterator.next(); // First handle is usually the original window
        String second = iterator.next(); // Second handle is the newly opened tab
        String new_tab = iterator.next();
        // Switch to the new tab
        driver.switchTo().window(new_tab);

        String newTabTitle = driver.getTitle();
        System.out.println("Title of the new tab: " + newTabTitle);
        driver.close();
        driver.switchTo().window(originalWindow);

        assert newTabTitle != null;
        return newTabTitle.contains("YouTube");
    }

}
