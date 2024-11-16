package org.testNG;

import org.baseClass.baseClassForTestNG;
import org.testng.Assert;
import org.testng.annotations.Test;

public class home extends baseClassForTestNG {




    @Test(priority = 1)
    public void verifyHomepageElements(){
        try{
            logger.info("***** Verify the home page elements start *****");
            logger.info("***** Successfully logged for validate the UI on home page *****");
            login.login(p.getProperty("username"),p.getProperty("password"));
            login.clickOnLogButton();
            logger.info("***** Validate the UI related elements *****");
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
            logger.info("***** Finished the verification *****");

        }catch (Exception e){
            logger.error("***** Test Failed *****{}", e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testHeaderSectionUI(){
        try {
            logger.info("***** Test header UI is started *****");
            login.login(p.getProperty("username"),p.getProperty("password"));
            login.clickOnLogButton();
            Assert.assertEquals(driver.getCurrentUrl(),"https://qamoviesapp.ccbp.tech/");
            logger.info("***** Validate logo is displayed or not and header elements are empty or not");
            Assert.assertTrue(homepage.isLogoDisplayed(), "Website logo is not displayed");
            Assert.assertTrue(homepage.getNavbarElements().size() > 0, "Navbar elements are not present");
            logger.info("***** Test header UI is finished *****");

        }catch (Exception e){
            logger.error("***** Test case is failed due to " + e.getMessage() + "*****");
        }
    }

    @Test(priority = 3, groups = "sanity")
    public void testHeaderSectionFunctionalities(){
        try {
            logger.info("***** Validate the functionality of header section is start *****");
            logger.info("***** Successfully login *****");
            login.login(p.getProperty("username"),p.getProperty("password"));
            login.clickOnLogButton();
            logger.info("***** Click on home element *****");
            homepage.clickHomeLink();
            logger.info("***** Validate URL of home page *****");
            Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/");
            logger.info("***** Click on popular element *****");
            homepage.clickPopularLink();
            logger.info("***** Validate URL of popular page *****");
            Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/popular");
            logger.info("***** Click on account element *****");
            homepage.clickAccountLink();
            logger.info("***** Validate URL of account page  *****");
            Assert.assertEquals(homepage.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/account");
            logger.info("***** Validate the functionality of header section is finished *****");

        }catch (Exception e){
            logger.error("***** Test is failed due to" + e.getMessage() + "*****");
        }

    }


}
