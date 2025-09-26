package com.springselenium.automation.cucumber.steps;
import com.springselenium.automation.pages.nasa.*;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class NasaSteps {
    @Autowired
    NasaPage nasaPage;

    @Autowired
    NasaResultsPage nasaResultsPage;

    @Autowired
    NasaTechnologyPage nasaTechnologyPage;

    @Autowired
    NasaImagePage nasaImagePage;

    @Autowired
    NasaContactPage nasaContactPage;

    @Autowired
    NasaSubmitQuestionPage nasaSubmitQuestionPage;

    @Given("I am on the Nasa homepage")
    public void given_IAmOnTheNasaHomepage() {
        nasaPage.goTo();
        Assert.assertTrue(nasaPage.isAt());
    }
    @When("I use the search function with the keyword {string}")
    public void when_IUseTheSearchFunctionWithTheKeyword(String keyword) {
        // Write code here that turns the phrase above into concrete actions
        nasaPage.search(keyword);
        nasaPage.clickSearch();
    }
    @And("apply a filter by Articles content type")
    public void and_ApplyAFilterByContentType() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        nasaResultsPage.filterByContentType();
        nasaResultsPage.clickApplyFilter();
    }
    @Then("the results match the search text and the filter criteria")
    public void then_TheResultsMatchTheSearchTextAndTheFilterCriteria() {
        // Write code here that turns the phrase above into concrete actions
        String expectedResultTitle = "Search Results for: Mars Rover";
        String actualResultTitle = nasaResultsPage.getResultsTitle();
        Assert.assertEquals(expectedResultTitle,actualResultTitle);

        String expectedContentType = "Articles";
        String actualContentType = nasaResultsPage.getContentType();
        Assert.assertEquals(expectedContentType,actualContentType);

    }

    @When("I click Explore")
    public void when_IClickExplore() {
        nasaPage.clickExplore();
    }

    @And("Click Technology")
    public void and_ClickTechnology() {
        nasaPage.clickTechnology();
    }

    @Then("The technology page loads with the correct header")
    public void then_TheTechnologyPageLoadsWithTheCorrectHeader() throws InterruptedException {
        Thread.sleep(2000);
        String expected_pageTile = "Technology - NASA";
        String actual_pageTitle = nasaTechnologyPage.getPageTitle();
        Assert.assertEquals(expected_pageTile,actual_pageTitle);
    }
    @And("at least one article visible")
    public void and_AtLeastOneArticleVisible() {
        int number_articles = nasaTechnologyPage.getArticleNumber();
        Assert.assertTrue(number_articles>0);
    }

    @When("I click Multimedia")
    public void when_IClickMultimedia() {
        nasaPage.clickMultimedia();
    }

    @And("Click Image of the Day")
    public void and_ClickImageOfTheDay() {
        nasaPage.clickImageOfDay();
    }

    @And("Click on a random featured image or video")
    public void and_ClickOnARandomFeaturedImageOrVideo() throws InterruptedException {
        nasaImagePage.click_image();
    }

    @Then("The image or video opens correctly")
    public void then_TheImageVideoOpensCorrectly() {
        Assert.assertNotNull(nasaImagePage.getImageAltText());
    }

    @When("I navigate to the Nasa Contact Form")
    public void when_INavigateToTheNasaContactForm() throws InterruptedException {
        nasaPage.clickContactNasa();
        nasaContactPage.clickContactNasa();
    }

    @And("I fill in some fields with the following test data {string} {string} {string} {string}")
    public void and_IFillInSomeFieldsWithTheFollowingTestData(String fname, String lname, String email, String comment) {
        nasaSubmitQuestionPage.fillOutFormData(fname, lname, email, comment);
    }

    @And("I click Submit")
    public void and_IClickSubmit() throws InterruptedException {
        nasaSubmitQuestionPage.clickSubmit();
    }

    @Then("The form is submitted successfully")
    public void then_theFormIsSubmittedSuccessfully() {
        String expected_message = "Thanks for contacting us! We will get in touch with you shortly.";
        String actual_message = nasaSubmitQuestionPage.confirmationMessage();
        Assert.assertEquals(expected_message,actual_message);
    }

    @And("Click each of the Multimedia submenu links")
    public void and_clickEachOfTheSubmenuLinks() throws InterruptedException {
        nasaPage.clickEachMultimediaLink();
    }

    @Then("Each page will load without errors")
    public void then_eachPageWillLoadWithoutErrors() {
        Assert.assertFalse(nasaPage.validateAnyErrorPageLoad());
    }

    @When("I click on the social media links")
    public void when_iClickOnTheSocialMediaLinks() throws InterruptedException {
        nasaPage.clickSocialMediaLinks();
    }

    @Then("They open correctly")
    public void then_theyOpenCorrectly() {
        Assert.assertFalse(nasaPage.validateAnyErrorSocialMediaLinks());
    }

    @When("I click View image of the day")
    public void when_IClickViewImageOfTheDay() throws InterruptedException {
        nasaPage.clickImageOfTheDay();
    }
    @And("I click Download")
    public void and_IClickDownload() throws InterruptedException {
        nasaPage.downloadImage();
    }
    @Then("The image is downloaded successfully")
    public void then_TheImageIsDownloadedSuccessfully() {
        Assert.assertFalse(nasaPage.validateAnyErrorImageDownload());
    }

}
