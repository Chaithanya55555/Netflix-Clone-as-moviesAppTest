package org.testNG;


import org.baseClass.baseClassForTestNG;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class popular  extends baseClassForTestNG {


    @Test(priority = 1)
    public void verifyMoviesAreDisplayedOnThePopularPage(){
        Assert.assertTrue(login.areInputEmpty());
        login.login("rahul" , "rahul@2021");
        login.clickOnLogButton();
        popularPage.clickOnHomePage();
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/popular");
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/popular");
        Assert.assertTrue(popularPage.areMoviesDisplayed());

    }

    @Test(priority = 2)
    public void VerifyUIElementsOnThePopularPage(){
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/login");
        login.login("rahul" , "rahul@2021");
        login.clickOnLogButton();
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/");
        homepage.clickOnMovie();
        Assert.assertFalse(homepage.getMovieTitle().isEmpty(), "Movie title is not displayed");
        Assert.assertFalse(homepage.getMovieDescription().isEmpty(), "Movie description is not displayed");
        Assert.assertFalse(homepage.getMovieRating().isEmpty(), "Movie rating is not displayed");
        try{
            Assert.assertTrue(homepage.isPlayButtonDisplayed(), "Play button is not displayed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(homepage.getGenreHeading(),"Genres");
        Assert.assertEquals(homepage.getGenre() ,"Drama");
        Assert.assertEquals(homepage.getAudioHeading(),"Audio Available");
        Assert.assertEquals( homepage.getAudioAvailable(),"French");
        Assert.assertEquals(homepage.getRatingCountHeading(),"Rating Count");
        Assert.assertEquals( homepage.getRatingCount(),"39");
        Assert.assertEquals(homepage.getAverageRatingHeading(),"Rating Average");
        Assert.assertEquals( homepage.getRatingAverage(),"7.6");
        Assert.assertEquals(homepage.getBudgetHeading(),"Budget");
        Assert.assertEquals( homepage.getBudget(),"1.6 Crores");
        Assert.assertEquals(homepage.getReleaseDateHeading(),"Release Date");
        Assert.assertEquals( homepage.getReleaseDate(),"25th March 1981");

    }


}
