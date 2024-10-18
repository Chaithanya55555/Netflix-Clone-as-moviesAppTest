package org.testNG;

import org.baseClass.baseClassForTestNG;
import org.testng.Assert;
import org.testng.annotations.Test;

public class home extends baseClassForTestNG {




    @Test(priority = 1)
    public void verifyHomepageElements(){
        String url = "https://qamoviesapp.ccbp.tech";
        driver.get(url);
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
        Assert.assertEquals(homepage.getMainHeading(),"Trending Now");
        Assert.assertEquals(homepage.getSideHeading(),"Originals");
        Assert.assertTrue(homepage.isPlayButtDisplayed());
        Assert.assertTrue(homepage.arePopularMoviesDisplayed());
        Assert.assertTrue(homepage.verifyTrendingMoviesAreDisplayedInCorresponding());
        Assert.assertTrue(homepage.areTopRatedMoviesDisplayed());
        Assert.assertTrue(homepage.verifyOriginalMoviesAreDisplayedInCorresponding());
        Assert.assertTrue(homepage.isContactUsSectionPresent());
        Assert.assertEquals(homepage.getText(),"Contact Us");
        Assert.assertTrue(homepage.isContactFormFunctional());
    }

    @Test(priority = 2)
    public void testHeaderSectionUI(){
        String url = "https://qamoviesapp.ccbp.tech";
        driver.get(url);
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
        Assert.assertTrue(homepage.isLogoDisplayed(), "Website logo is not displayed");
        Assert.assertTrue(homepage.getNavbarElements().size() > 0, "Navbar elements are not present");
    }

    @Test(priority = 3)
    public void testHeaderSectionFunctionalities(){
        String url = "https://qamoviesapp.ccbp.tech";
        driver.get(url);
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
        homepage.clickHomeLink();
        Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/");
        homepage.clickPopularLink();
        Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/popular");
        homepage.clickAccountLink();
        Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/account");

    }


}
