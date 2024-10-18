package org.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.loginPage;
import org.pages.popularPage;
import org.testng.Assert;
import org.pages.homePage;

import java.time.Duration;

public class PopularPageTest {
    public WebDriver driver;
    private popularPage popularPage;
    public loginPage login;
    public homePage homePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        login = new loginPage(driver);
        homePage = new homePage(driver);
        popularPage = new popularPage(driver);
        driver.get("https://qamoviesapp.ccbp.tech/login");
        login.login("rahul", "rahul@2021");
        login.clickOnLogButton();

    }


    @Given("I am on the movie website popular page")
    public void i_am_on_the_movie_website_homepage() {
        popularPage.clickOnHomePage();
    }

    @When("I look at the movie list section")
    public void i_look_at_the_movie_list_section() {
        homePage.waits(5, "https://qamoviesapp.ccbp.tech/popular");
        Assert.assertEquals(homePage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/popular");
    }

    @Then("I should see a list of movies displayed")
    public void i_should_see_a_list_of_movies_displayed() {
        Assert.assertTrue(popularPage.areMoviesDisplayed());
    }

    @After
    public void tearDown()  {
        if (driver != null) {
            driver.quit();
        }
    }
}
