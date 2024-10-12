package org.testNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.homePage;
import org.pages.loginPage;
import org.pages.popularPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class popular {
    public WebDriver driver;
    private popularPage popularPage;
    public loginPage login;
    public org.pages.homePage homePage;
    WebDriverWait wait;
    homePage home;

    @Parameters({"br"})
    @BeforeMethod
    public void setup(String br){
        switch (br.toLowerCase()){
            case "chrome": WebDriverManager.chromedriver().setup();driver = new ChromeDriver();break;
            case "edge":WebDriverManager.edgedriver().setup();driver = new EdgeDriver();break;
            default: System.out.println("Invalid browser");return;
        }
        login = new loginPage(driver);
        home = new homePage(driver);
        popularPage = new popularPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void verifyMoviesAreDisplayedOnThePopularPage(){
        driver.get("https://qamoviesapp.ccbp.tech");
        Assert.assertTrue(login.areInputEmpty());
        login.login("rahul" , "rahul@2021");
        login.clickOnLogButton();
        popularPage.clickOnHomePage();
        wait.until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/popular"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/popular");
        Assert.assertTrue(popularPage.areMoviesDisplayed());

    }

    @Test(priority = 2)
    public void VerifyUIElementsOnThePopularPage(){
        driver.get("https://qamoviesapp.ccbp.tech/login");
        home.waits(5,"https://qamoviesapp.ccbp.tech/login");
        login.login("rahul" , "rahul@2021");
        login.clickOnLogButton();
        home.waits(5,"https://qamoviesapp.ccbp.tech/");
        home.clickOnMovie();
        Assert.assertFalse(home.getMovieTitle().isEmpty(), "Movie title is not displayed");
        Assert.assertFalse(home.getMovieDescription().isEmpty(), "Movie description is not displayed");
        Assert.assertFalse(home.getMovieRating().isEmpty(), "Movie rating is not displayed");
        try{
            Assert.assertTrue(home.isPlayButtonDisplayed(), "Play button is not displayed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
