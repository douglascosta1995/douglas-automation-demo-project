package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@LazyComponent
public class NasaSubmitQuestionPage extends AbstractPage {

    private final By nasa_header = By.id("header-logo");

    private final By input_first_name = By.id("input_18_3_3");

    private final By input_last_name = By.id("input_18_3_6");

    private final By input_email = By.id("input_18_4");

    private final By select_subject = By.id("input_18_5");

    private final By input_comment_box = By.id("input_18_6");

    private final By button_submit = By.id("gform_submit_button_18");

    private final By confirmation_message = By.id("gform_confirmation_message_18");

    @Override
    public boolean isAt() {
        return wait.until((d) -> driver.findElement(nasa_header).isDisplayed());
    }

    public void fillOutFormData(String fname, String lname, String email, String comment){
        driver.findElement(input_first_name).sendKeys(fname);
        driver.findElement(input_last_name).sendKeys(lname);
        driver.findElement(input_email).sendKeys(email);

        WebElement dropdown = driver.findElement(select_subject);
        Select select = new Select(dropdown);
        select.selectByIndex(8);

        driver.findElement(input_comment_box).sendKeys(comment);
    }

    public void clickSubmit() {
        scroll_into_view_and_click(button_submit);
        wait_page_to_load();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation_message));

    }

    public String confirmationMessage(){
        return driver.findElement(confirmation_message).getText();
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
    public void wait_page_to_load(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
