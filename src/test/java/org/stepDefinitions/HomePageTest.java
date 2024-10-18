package org.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.homePage;
import org.pages.loginPage;
import org.testng.Assert;

import java.time.Duration;

public class HomePageTest {
    private WebDriver driver;
    private homePage homepage;
    private loginPage login;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homepage = new homePage(driver);
        login = new loginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage()  {
        String url = "https://qamoviesapp.ccbp.tech";
        driver.get(url);
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
    }

    @When("I should see correct heading texts for each section")
    public void i_should_see_the_correct_heading_for_each_section()  {
        Assert.assertEquals(homepage.getMainHeading(),"Trending Now");
        Assert.assertEquals(homepage.getSideHeading(),"Originals");
    }

    @And("the play button should be displayed")
    public void the_play_button_should_be_displayed() {
        Assert.assertTrue(homepage.isPlayButtDisplayed());
    }

    @And("movies should be displayed in corresponding sections")
    public void movies_should_be_displayed_in_their_corresponding_sections()  {
        Assert.assertTrue(homepage.arePopularMoviesDisplayed());
        Assert.assertTrue(homepage.verifyTrendingMoviesAreDisplayedInCorresponding());
        Assert.assertTrue(homepage.areTopRatedMoviesDisplayed());
        Assert.assertTrue(homepage.verifyOriginalMoviesAreDisplayedInCorresponding());
    }

    @And("the Contact Us section should be present")
    public void the_contact_us_section_should_be_present_and_functional(){
        Assert.assertTrue(homepage.isContactUsSectionPresent());
        Assert.assertEquals(homepage.getText(),"Contact Us");
        Assert.assertTrue(homepage.isContactFormFunctional());
    }


    @When("the website logo should be displayed")
    public void theWebsiteLogoShouldBeDisplayed()  {
        Assert.assertTrue(homepage.isLogoDisplayed(), "Website logo is not displayed");
    }

    @And("the navbar elements should be visible")
    public void theNavbarElementsShouldBePresent() {
        Assert.assertTrue(homepage.getNavbarElements().size() > 0, "Navbar elements are not present");
    }

    @When("the user clicks on the Home link in the header")
    public void theUserClicksOnTheHomeLink() {
        homepage.clickHomeLink();
    }

    @Then("the user should be navigated to the Home page")
    public void theUserShouldBeNavigatedToTheHomePage() {
        Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/");
    }

    @When("the user clicks on the Popular link in the header")
    public void theUserClicksOnThePopularLink() {
        homepage.clickPopularLink();
    }

    @Then("the user should be navigated to the Popular page")
    public void theUserShouldBeNavigatedToThePopularPage() {
        Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/popular");
    }

    @When("the user clicks on the Account link in the header")
    public void theUserClicksOnTheAccountLink() {
        homepage.clickAccountLink();
    }

    @Then("the user should be navigated to the Account page")
    public void theUserShouldBeNavigatedToTheAccountPage() {
        Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/account");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
