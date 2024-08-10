Feature: Movie Display Functionality

  Scenario: Verify movies are displayed on the popular page
    Given I am on the movie website popular page
    When I look at the movie list section
    Then I should see a list of movies displayed

  Scenario: Verify UI elements on the popular Page
    Given I am on the movie website in popular page
    When I click on the any movie in popular page
    Then I should see the movie title in popular page
    And I should see the movie description in popular page
    And I should see the movie rating in popular page
    And I should see the play button in popular page
    And Verify other details of the movie in popular page