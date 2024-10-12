package org.testNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.homePage;
import org.pages.loginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class home {
    private WebDriver driver;
    private homePage homepage;
    private loginPage login;
    public WebDriverWait wait;

    @Parameters({"br"})
    @BeforeMethod
    public void setup(String br ){
        switch (br.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();driver = new ChromeDriver();break;
            case "edge":WebDriverManager.edgedriver().setup();driver = new EdgeDriver();break;
            default: System.out.println("Invalid browser");return;
        }
        login = new loginPage(driver);
        homepage = new homePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

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

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
