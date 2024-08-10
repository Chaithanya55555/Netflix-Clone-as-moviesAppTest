package org.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.homePage;
import org.pages.loginPage;
import org.testng.Assert;
import org.pages.searchPage;

import java.time.Duration;

public class SearchPage {
    public WebDriver driver;
    private searchPage search;
    private loginPage login;
    private homePage home;
    private int movieCount;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        login = new loginPage(driver);
        search = new searchPage(driver);
        home = new homePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://qamoviesapp.ccbp.tech/login");
        login.login("rahul", "rahul@2021");
        login.clickOnLogButton();
    }

    @Given("I am on the movie search page")
    public void iAmOnTheMovieSearchPage() {

        search.clickEmptySearchButton();
        home.waits(5, "https://qamoviesapp.ccbp.tech/search");
    }

    @When("I search for the movie {string}")
    public void iSearchForTheMovie(String movieName) {
        search.enterSearchQuery(movieName);
        search.clickSearchButton();
    }

    @Then("I should see search results")
    public void iShouldSeeSearchResults() {
        movieCount = search.getMovieResultsCount();
        Assert.assertTrue(movieCount > 0, "No search results found");
    }

    @And("the number of movies displayed should be recorded")
    public void theNumberOfMoviesDisplayedShouldBeRecorded() {
        System.out.println("Number of movies displayed: " + movieCount);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue(search.isErrorMessageDisplayed(), "Error message is not displayed");
    }

    @And("I should see an error image")
    public void iShouldSeeAnErrorImage() {
        Assert.assertTrue(search.isErrorImageDisplayed(), "Error image is not displayed");
    }

    @And("the error message should indicate no results found")
    public void theErrorMessageShouldIndicateNoResultsFound() {
        String errorText = search.getErrorMessageText();
        Assert.assertTrue(errorText.contains(" did not find any matches."), "Error message does not indicate 'No results found'");
    }

    @After
    public void  tearDown(){
        try {
            if (driver != null) {
                driver.quit();
            }

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }
}

