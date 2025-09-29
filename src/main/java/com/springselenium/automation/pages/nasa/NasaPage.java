package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@LazyComponent
public class NasaPage extends AbstractPage {
    @Value("${application.url}")
    private String url;

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

    private final By button_view_image = By.xpath("//div[contains(@class, 'under-image-button')]//a");

    private final By button_download = By.xpath("//*[contains(@class, 'hds-button-download')]");

    private final By button_recently_published = By.xpath("//a[contains(@class, 'button-primary')]/span[contains(text(), 'Recently Published')]");

    private final By news_header = By.className("hds-a11y-heading-22");

    public static boolean errorLoadingPage = false;
    public static boolean errorSocialMediaLinks = false;
    public static boolean errorDownloadImage= false;


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
        wait.until((ExpectedCondition<Boolean>) wd ->
                wd.getTitle() != null && !wd.getTitle().trim().isEmpty());
    }

    public void clickMultimedia(){
        driver.findElement(button_multimedia).click();
    }

    public void clickImageOfDay(){
        driver.findElement(button_image_of_day_submenu).click();
    }

    public void clickContactNasa() {
        scroll_into_view_and_click(button_contact_nasa);
    }

    public void clickEachMultimediaLink() {
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

    public boolean clickFacebookValidateLink(By facebook_link) {
        wait_page_to_load();
        String originalWindow = driver.getWindowHandle();
        scroll_to_bottom();
        scroll_into_view_and_click(facebook_link);

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab (assuming it's the second one)
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                while (true) {
                    String title = driver.getTitle();
                    if (title != null && title.contains("Slido for Google Slides")) {
                        System.out.println("Detected Slido overlay title, refreshing...");
                        driver.navigate().refresh();

                        // Small wait to let the refresh complete
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        break; // Exit loop once valid title is found
                    }
                }
            }
        }

        String newTabTitle = driver.getTitle();
        System.out.println("Title of the new tab: " + newTabTitle);
        close_tab();
        driver.switchTo().window(originalWindow);

        assert newTabTitle != null;
        return newTabTitle.contains("Facebook");
    }

    public boolean clickInstagramValidateLink(By instagram_link) {
        wait_page_to_load();
        String originalWindow = driver.getWindowHandle();
        scroll_to_bottom();
        scroll_into_view_and_click(instagram_link);

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab (assuming it's the second one)
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                while (true) {
                    String title = driver.getTitle();
                    if (title != null && title.contains("Slido for Google Slides")) {
                        System.out.println("Detected Slido overlay title, refreshing...");
                        driver.navigate().refresh();

                        // Small wait to let the refresh complete
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        break; // Exit loop once valid title is found
                    }
                }
            }
        }

        String newTabTitle = driver.getTitle();
        System.out.println("Title of the new tab: " + newTabTitle);
        close_tab();
        driver.switchTo().window(originalWindow);

        assert newTabTitle != null;
        return newTabTitle.contains("Instagram");
    }

    public boolean clickXValidateLink(By x_link) {
        wait_page_to_load();
        String originalWindow = driver.getWindowHandle();
        scroll_to_bottom();
        scroll_into_view_and_click(x_link);

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab (assuming it's the second one)
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                while (true) {
                    String title = driver.getTitle();
                    if (title != null && title.contains("Slido for Google Slides")) {
                        System.out.println("Detected Slido overlay title, refreshing...");
                        driver.navigate().refresh();

                        // Small wait to let the refresh complete
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        break; // Exit loop once valid title is found
                    }
                }
            }
        }

        String newTabTitle = driver.getTitle();

        System.out.println("Title of the new tab: " + newTabTitle);
        close_tab();
        driver.switchTo().window(originalWindow);

        assert newTabTitle != null;
        return newTabTitle.contains("/ X");
    }

    public boolean clickYoutubeValidateLink(By youtube_link) {
        wait_page_to_load();
        String originalWindow = driver.getWindowHandle();
        scroll_to_bottom();
        scroll_into_view_and_click(youtube_link);

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab (assuming it's the second one)
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                while (true) {
                    String title = driver.getTitle();
                    if (title != null && title.contains("Slido for Google Slides")) {
                        System.out.println("Detected Slido overlay title, refreshing...");
                        driver.navigate().refresh();

                        // Small wait to let the refresh complete
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        break; // Exit loop once valid title is found
                    }
                }
            }
        }

        String newTabTitle = driver.getTitle();
        System.out.println("Title of the new tab: " + newTabTitle);
        close_tab();
        driver.switchTo().window(originalWindow);

        assert newTabTitle != null;
        return newTabTitle.contains("YouTube");
    }

    public void clickImageOfTheDay() {
        wait_page_to_load();
        scroll_into_view_and_click(button_view_image);
    }

    public void downloadImage() {
        String downloadPath = "/Users/douglascosta/Documents/Automation/douglas-automation-demo-project/src/main/downloads";
        File downloadFiles = new File(downloadPath);
        clearDownloadFolder(downloadFiles);
        driver.findElement(button_download).click();
        waitUntilFolderIsNotEmpty(downloadPath, Duration.ofSeconds(30));
    }

    public boolean validateAnyErrorImageDownload(){
        return errorDownloadImage;
    }

    public void clickRecentlyPublished() {
        scroll_into_view_and_click(button_recently_published);
    }

    public int listOfPublishedNews(){
        List<WebElement> news_header_list = driver.findElements(news_header);
        return news_header_list.size();
    }


    /// Helper Functions ///

    public void scroll_into_view_and_click(By button_to_be_clicked){
        WebElement button = driver.findElement(button_to_be_clicked);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "var elem = arguments[0], box = elem.getBoundingClientRect();" +
                                "return (box.top >= 0 && box.bottom <= window.innerHeight);", button));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", button);

        wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "var elem = arguments[0], box = elem.getBoundingClientRect();" +
                                "return (box.top >= 0 && box.bottom <= window.innerHeight);", button));

        wait.until(ExpectedConditions.elementToBeClickable(button));
        wait.until(ExpectedConditions.visibilityOf(button));

        button.click();
    }

    public void scroll_to_bottom(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void wait_page_to_load(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void close_tab(){
        wait.until(d -> {
            String title = d.getTitle();
            return title != null && !title.isEmpty() && !title.contains("Slido for Google Slides");
        });
        try {
            driver.close();
        } catch (RuntimeException e) {
            System.err.println("Standard driver.close() failed: " + e.getMessage());
            try {
                ((JavascriptExecutor) driver).executeScript("window.close()");
                System.out.println("Closed tab using JavaScript fallback.");
            } catch (Exception jsEx) {
                System.err.println("JavaScript close also failed: " + jsEx.getMessage());
            }
        }
    }

    public void clearDownloadFolder(File downloadFolder){
        if (downloadFolder.exists() && downloadFolder.isDirectory()) {
            File[] files = downloadFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Recursively delete contents of subdirectories
                        clearDownloadFolder(file);
                    }
                    file.delete(); // Delete the file or empty directory
                }
            }
        }
    }

    public static void waitUntilFolderIsNotEmpty(String folderPath, Duration timeout) {
        File folder = new File(folderPath);
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeout.toMillis()) {
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null && files.length > 0) {
                    System.out.println("Folder is not empty. Found " + files.length + " files.");
                    return; // Folder is not empty, exit the loop
                }
            }
            try {
                Thread.sleep(500); // Wait for a short interval before re-checking
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted while waiting for folder to be non-empty.");
                errorDownloadImage = true;
                return;
            }
        }
        errorDownloadImage = true;
        System.out.println("Timeout reached. Folder is still empty or does not exist.");
    }

}
