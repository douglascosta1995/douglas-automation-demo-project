Feature: As a user, I want to explore the Nasa Page and have all of its functionality working well

  Scenario: Validate that Search and Filters are working
    Given I am on the Nasa homepage
    When I use the search function with the keyword "Mars Rover"
    And apply a filter by Articles content type
    Then the results match the search text and the filter criteria

  Scenario: Validate that Multi-level Navigation Menu is working
    Given I am on the Nasa homepage
    When I click Explore
    And Click Technology
    Then The technology page loads with the correct header
    And at least one article visible
