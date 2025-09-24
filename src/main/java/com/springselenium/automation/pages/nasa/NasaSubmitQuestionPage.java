package com.springselenium.automation.pages.nasa;

import com.springselenium.automation.annotation.LazyComponent;
import com.springselenium.automation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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

    public void clickSubmit() throws InterruptedException {
        WebElement button = driver.findElement(button_submit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        Thread.sleep(5000);
        button.click();
        Thread.sleep(5000);
    }

    public String confirmationMessage(){
        return driver.findElement(confirmation_message).getText();
    }
}
