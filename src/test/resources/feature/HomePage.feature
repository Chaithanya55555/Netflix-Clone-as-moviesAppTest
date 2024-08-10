# src/test/resources/features/homepage.feature
Feature: Homepage Functionality && Header Section Tests

  Scenario: Verify homepage elements
    Given I am on the homepage
    When I should see correct heading texts for each section
    And the play button should be displayed
    And movies should be displayed in corresponding sections
    And the Contact Us section should be present

    # HeaderSection.feature
  Scenario: Test Header Section UI
    Given I am on the homepage
    When the website logo should be displayed
    And the navbar elements should be visible


  Scenario: Test Header Section Functionalities
    Given I am on the homepage
    When the user clicks on the Home link in the header
    Then the user should be navigated to the Home page
    When the user clicks on the Popular link in the header
    Then the user should be navigated to the Popular page
    When the user clicks on the Account link in the header
    Then the user should be navigated to the Account page

  Scenario: Verify UI elements on the Movie Page
    Given I am on the home page
    When I click on the any movie
    Then I should see the movie title
    And I should see the movie description
    And I should see the movie rating
    And I should see the play button
    And Verify other details of the movie

