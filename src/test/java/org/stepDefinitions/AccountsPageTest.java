package org.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.accountsPage;
import org.pages.homePage;
import org.pages.loginPage;
import org.testng.Assert;

import java.time.Duration;

public class AccountsPageTest {
    private final String baseUrl = "https://qamoviesapp.ccbp.tech/login";
    private loginPage login;
    private accountsPage accountsPage;
    private WebDriver driver;
    private homePage home;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        login = new loginPage(driver);
        home = new homePage(driver);
        accountsPage = new accountsPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Given("I am on the home page of the website")
    public void navigateHomePage(){
        driver.get(baseUrl);
        home.waits(5,"https://qamoviesapp.ccbp.tech/login");
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
        home.waits(5,"https://qamoviesapp.ccbp.tech/");
    }

    @When("I click on avatar button")
    public void clickOnAvatarButton(){
        home.clickAccountLink();
        home.waits(5,"https://qamoviesapp.ccbp.tech/account");
    }

    @And("Verify the user interface elements")
    public void verifyEveryElementOnThePage(){
        Assert.assertEquals( accountsPage.getAccountHeadingText(),"Account");
        Assert.assertEquals( accountsPage.getMembershipHeadingText(),"Member ship");
        Assert.assertEquals( accountsPage.getUsernameText(),"User name : rahul");
        Assert.assertEquals( accountsPage.getPasswordText(),"Password : **********");
        Assert.assertEquals( accountsPage.getPlanHeadingText(),"Plan details");
        Assert.assertEquals( accountsPage.getPlanTypeText(),"Premium");
        Assert.assertEquals( accountsPage.getPlanDetailsText(),"Ultra HD");
    }

    @Given("I am on the account page")
    public void navigateAccountPage(){
        driver.get(baseUrl);
        home.waits(5,"https://qamoviesapp.ccbp.tech/login");
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
        home.waits(5,"https://qamoviesapp.ccbp.tech/");
        home.clickAccountLink();
        home.waits(5,"https://qamoviesapp.ccbp.tech/account");
    }

    @When("I click on the logout button")
    public void clickOnLogoutButton(){
        accountsPage.clickLogoutButton();
    }

    @Then("Successfully logout")
    public void verifyLogoutOrNot(){
        String expectedLogoutUrl = "https://qamoviesapp.ccbp.tech/login";
        Assert.assertEquals(expectedLogoutUrl, driver.getCurrentUrl());

    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
