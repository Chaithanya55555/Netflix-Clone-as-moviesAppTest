Feature: UI and Login Functionality
  As a user of the e-commerce website
  I want to be able to visible all text and images
  So that I can see all texts and logo
  Scenario: User interface
    Given I am on the movie app login page
    When Website logo image is displayed,Heading text is "Login",Username label text is "USERNAME",Password label text is "PASSWORD"
    And "Login" button
  Scenario: Successful Login
    Given I am on the movie app login page
    When I enter valid username and password,I click on the login button
    Then I should be redirected to the home page
  Scenario: Unsuccessful Login
    Given I am on the movie app login page
    When I enter valid username and invalid password,I click on the login button
    Then Error will be  message appear

