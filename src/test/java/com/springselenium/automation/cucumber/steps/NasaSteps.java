package com.springselenium.automation.cucumber.steps;
import com.springselenium.automation.pages.nasa.NasaPage;
import com.springselenium.automation.pages.nasa.NasaResultsPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class NasaSteps {
    @Autowired
    NasaPage nasaPage;

    @Autowired
    NasaResultsPage nasaResultsPage;

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
    public void i_click_explore() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @And("Click Technology")
    public void click_technology() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("The technology page loads with the correct header")
    public void the_technology_page_loads_with_the_correct_header() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @And("at least one article visible")
    public void at_least_one_article_visible() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
