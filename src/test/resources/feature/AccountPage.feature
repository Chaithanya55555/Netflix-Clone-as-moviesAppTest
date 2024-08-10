Feature: Logout && UI elements are present
  Scenario:
    Given I am on the home page of the website
    When I click on avatar button
    And Verify the user interface elements
  Scenario:
    Given I am on the account page
    When I click on the logout button
    Then Successfully logout