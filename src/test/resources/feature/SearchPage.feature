# src/test/resources/features/movie_search.feature
Feature: Movie Search Functionality

  Scenario Outline: Search for valid movie names
    Given I am on the movie search page
    When I search for the movie "<movieName>"
    Then I should see search results
    And the number of movies displayed should be recorded

    Examples:
      | movieName         |
      | Titanic          |
      | Red notice        |
      | Luca              |

  Scenario Outline: Search for invalid movie names
    Given I am on the movie search page
    When I search for the movie "<invalidMovieName>"
    Then I should see an error message
    And I should see an error image
    And the error message should indicate no results found

    Examples:
      | invalidMovieName           |
      | xyzabcnonexistentmovie123  |
      | !@#$%^&*()                 |