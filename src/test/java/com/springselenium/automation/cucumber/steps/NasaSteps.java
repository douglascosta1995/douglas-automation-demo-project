package com.springselenium.automation.cucumber.steps;
import com.springselenium.automation.pages.nasa.NasaPage;
import com.springselenium.automation.pages.nasa.NasaResultsPage;
import com.springselenium.automation.pages.nasa.NasaTechnologyPage;
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
}
