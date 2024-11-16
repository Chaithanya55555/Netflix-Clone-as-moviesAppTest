package org.testNG;

import org.baseClass.baseClassForTestNG;
import org.testng.Assert;
import org.testng.annotations.Test;

public class moviesDetails extends baseClassForTestNG {

    @Test(priority = 1)
    public void UIOnMoviesDetailsOnHomePage(){
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/login");
        login.login("rahul" , "rahul@2021");
        login.clickOnLogButton();
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/");
        homepage.clickOnMovie();
        try{
            Assert.assertFalse(homepage.getMovieTitle().isEmpty(), "Movie title is not displayed");
            Assert.assertFalse(homepage.getMovieRating().isEmpty(), "Movie rating is not displayed");
            Assert.assertTrue(homepage.isPlayButtonDisplayed(), "Play button is not displayed");
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

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test(priority = 2)
    public void UIOnMoviesDetailsOnPopularPage(){
        homepage.waits(10,"https://qamoviesapp.ccbp.tech/login");
        login.login("rahul" , "rahul@2021");
        login.clickOnLogButton();
        homepage.waits(10,"https://qamoviesapp.ccbp.tech/");
        popularPage.clickOnHomePage();
        homepage.waits(10 ,"https://qamoviesapp.ccbp.tech/popular");
        popularPage.clickOnTheMovie();
        try{
            Assert.assertFalse(homepage.getMovieTitle().isEmpty(), "Movie title is not displayed");
            Assert.assertEquals(homepage.getMovieTitle(),"Godzilla vs. Kong");
            Assert.assertFalse(homepage.getMovieDescription().isEmpty(), "Movie description is not displayed");
            Assert.assertEquals(homepage.getMovieDescription(),"In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.");
            Assert.assertFalse(homepage.getMovieRating().isEmpty(), "Movie rating is not displayed");
            Assert.assertEquals(homepage.getRatingAverage(),"5.8");
            Assert.assertTrue(homepage.isPlayButtonDisplayed(), "Play button is not displayed");
            Assert.assertEquals(homepage.getGenreHeading(),"Genres");
            Assert.assertTrue(popularPage.verifyGenresTitle("Science Fiction"));
            Assert.assertTrue(popularPage.verifyGenresTitle("Thriller"));
            Assert.assertTrue(popularPage.verifyGenresTitle("Mystery"));
            Assert.assertEquals(homepage.getAudioHeading(),"Audio Available");
            Assert.assertEquals(homepage.getAudioAvailable(),"English");
            Assert.assertEquals(homepage.getRatingCountHeading(),"Rating Count");
            Assert.assertEquals( homepage.getRatingCount(),"88");
            Assert.assertEquals(homepage.getAverageRatingHeading(),"Rating Average");
            Assert.assertEquals( homepage.getRatingAverage(),"5.8");
            Assert.assertEquals(homepage.getBudgetHeading(),"Budget");
            Assert.assertEquals( homepage.getBudget(),"0.5 Crores");
            Assert.assertEquals(homepage.getReleaseDateHeading(),"Release Date");
            Assert.assertEquals( homepage.getReleaseDate(),"3rd September 2021");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
