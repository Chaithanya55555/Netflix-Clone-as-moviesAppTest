package org.stepDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.homePage;
import org.pages.loginPage;
import org.pages.popularPage;
import org.testng.Assert;

import java.time.Duration;


public class MovieDetailsPageTest {


    private WebDriver driver;
    private homePage home;
    public loginPage login;
    public popularPage popular;

    @Before
     public void setup(){
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         home = new homePage(driver);
         login = new loginPage(driver);
         popular = new popularPage(driver);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     }

    @Given("I am on the home page")
    public void navigateToHomePage() {
        driver.get("https://qamoviesapp.ccbp.tech/login");
        home.waits(5,"https://qamoviesapp.ccbp.tech/login");
        login.login("rahul" , "rahul@2021");
        login.clickOnLogButton();
        home.waits(5,"https://qamoviesapp.ccbp.tech/");

    }

    @When("I click on the any movie")
    public void navigateMoviePage() {
        home.clickOnMovie();
    }

    @Then("I should see the movie title")
    public void getMovieTitleOnHomePage(){
        Assert.assertFalse(home.getMovieTitle().isEmpty(), "Movie title is not displayed");
    }

    @And("I should see the movie description")
    public void getMovieDescription() {
        Assert.assertFalse(home.getMovieDescription().isEmpty(), "Movie description is not displayed");
    }

    @And("I should see the movie rating")
    public void getMovieRating() {
        Assert.assertFalse(home.getMovieRating().isEmpty(), "Movie rating is not displayed");
    }

    @And("I should see the play button")
    public void clickOnPlayButtonInMoviePage() {
         try{
             Assert.assertTrue(home.isPlayButtonDisplayed(), "Play button is not displayed");
         }catch (Exception e){
             System.out.println(e.getMessage());
         }

    }

    @And("Verify other details of the movie")
    public void verifyMoreDetailsAboutMovie(){
        Assert.assertEquals(home.getGenreHeading(),"Genres");
        Assert.assertEquals(home.getGenre() ,"Drama");
        Assert.assertEquals(home.getAudioHeading(),"Audio Available");
        Assert.assertEquals( home.getAudioAvailable(),"French");
        Assert.assertEquals(home.getRatingCountHeading(),"Rating Count");
        Assert.assertEquals( home.getRatingCount(),"39");
        Assert.assertEquals(home.getAverageRatingHeading(),"Rating Average");
        Assert.assertEquals( home.getRatingAverage(),"7.6");
        Assert.assertEquals(home.getBudgetHeading(),"Budget");
        Assert.assertEquals( home.getBudget(),"1.6 Crores");
        Assert.assertEquals(home.getReleaseDateHeading(),"Release Date");
        Assert.assertEquals( home.getReleaseDate(),"25th March 1981");

    }

    @Given("I am on the movie website in popular page")
    public void onThePopularPage(){
        driver.get("https://qamoviesapp.ccbp.tech/login");
        home.waits(10,"https://qamoviesapp.ccbp.tech/login");
        login.login("rahul" , "rahul@2021");
        login.clickOnLogButton();
        home.waits(10,"https://qamoviesapp.ccbp.tech/");
        popular.clickOnHomePage();
        home.waits(10 ,"https://qamoviesapp.ccbp.tech/popular");
    }

    @When("I click on the any movie in popular page")
    public void i_click_on_the_first_movie() {
        popular.clickOnTheMovie();
    }

    @Then("I should see the movie title in popular page")
    public void i_should_see_the_movie_title(){
        Assert.assertFalse(home.getMovieTitle().isEmpty(), "Movie title is not displayed");
        Assert.assertEquals(home.getMovieTitle(),"Godzilla vs. Kong");
    }

    @And("I should see the movie description in popular page")
    public void i_should_see_the_movie_description() {
        Assert.assertFalse(home.getMovieDescription().isEmpty(), "Movie description is not displayed");
        Assert.assertEquals(home.getMovieDescription(),"In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.");
    }

    @And("I should see the movie rating in popular page")
    public void i_should_see_the_movie_rating() {
        Assert.assertFalse(home.getMovieRating().isEmpty(), "Movie rating is not displayed");
        Assert.assertEquals(home.getRatingAverage(),"5.8");
    }

    @And("I should see the play button in popular page")
    public void i_should_see_the_play_button() {
        try{
            Assert.assertTrue(home.isPlayButtonDisplayed(), "Play button is not displayed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @And("Verify other details of the movie in popular page")
    public void Verify_other_details_of_the_movie(){
        Assert.assertEquals(home.getGenreHeading(),"Genres");
        Assert.assertTrue(popular.verifyGenresTitle("Science Fiction"));
        Assert.assertTrue(popular.verifyGenresTitle("Thriller"));
        Assert.assertTrue(popular.verifyGenresTitle("Mystery"));
        Assert.assertEquals(home.getAudioHeading(),"Audio Available");
        Assert.assertEquals(home.getAudioAvailable(),"English");
        Assert.assertEquals(home.getRatingCountHeading(),"Rating Count");
        Assert.assertEquals( home.getRatingCount(),"88");
        Assert.assertEquals(home.getAverageRatingHeading(),"Rating Average");
        Assert.assertEquals( home.getRatingAverage(),"5.8");
        Assert.assertEquals(home.getBudgetHeading(),"Budget");
        Assert.assertEquals( home.getBudget(),"0.5 Crores");
        Assert.assertEquals(home.getReleaseDateHeading(),"Release Date");
        Assert.assertEquals( home.getReleaseDate(),"3rd September 2021");

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
