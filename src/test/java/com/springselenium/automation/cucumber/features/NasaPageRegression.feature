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

  Scenario: Validate that External Link / Multimedia Content is working
    Given I am on the Nasa homepage
    When I click Multimedia
    And Click Image of the Day
    And Click on a random featured image or video
    Then The image or video opens correctly

  Scenario: Validate that the user can submit a question for NASA via a form
    Given I am on the Nasa homepage
    When I navigate to the Nasa Contact Form
    And I fill in some fields with the following test data "John" "Doe" "john.does@test.com" "Please do not reply, this is only a test."
    And I click Submit
    Then The form is submitted successfully

  Scenario: Validate that Internal Links load without errors
    Given I am on the Nasa homepage
    When Click each of the Multimedia submenu links
    Then Each page will load without errors

  Scenario: Validate that the social media links work
    Given I am on the Nasa homepage
    When I click on the social media links
    Then They open correctly

  Scenario: Validate that it is possible to View and Download Image Of The Day
    Given I am on the Nasa homepage
    When I click View image of the day
    And I click Download
    Then The image is downloaded successfully

  Scenario: Validate that clicking Recently Published Featured News takes the user to a list of News
    Given I am on the Nasa homepage
    When I click Recently Published button
    Then I am taken to a list of published news
