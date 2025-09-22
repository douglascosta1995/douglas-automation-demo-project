Feature: As a user, I want to explore the Nasa Page and have all of its functionality working well

  Scenario: Validate that Search and Filters are working
    Given I am on the Nasa homepage
    When I use the search function with the keyword "Mars Rover"
    And apply a filter by Articles content type
    Then the results match the search text and the filter criteria
