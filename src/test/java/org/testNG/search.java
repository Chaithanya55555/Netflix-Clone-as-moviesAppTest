package org.testNG;


import org.baseClass.baseClassForTestNG;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class search extends baseClassForTestNG {
    private int movieCount;

    @DataProvider(name = "moviesNames")
    public Object[] data(){
            return new Object[]{
                    "Titanic",
                    "Red notice",
                    "Luca",
                    "xyzabcnonexistentmovie123",
                    "!@#$%^&*()"
            };
    }

    @Test(priority = 1,dataProvider = "moviesNames",groups = "sanity")
    public void searchMovies(String movie){
        login.login("rahul", "rahul@2021");
        login.clickOnLogButton();
        search.clickEmptySearchButton();
        homepage.waits(5, "https://qamoviesapp.ccbp.tech/search");
        search.enterSearchQuery(movie);
        search.clickSearchButton();
        movieCount = search.getMovieResultsCount();
        if(movieCount > 0){
            try {
                Assert.assertTrue(movieCount > 0, "No search results found");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            Assert.assertTrue(search.isErrorMessageDisplayed(), "Error message is not displayed");
            String errorText = search.getErrorMessageText();
            Assert.assertTrue(errorText.contains(" did not find any matches."), "Error message does not indicate 'No results found'");
        }

    }
}
