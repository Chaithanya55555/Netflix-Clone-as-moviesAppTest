package org.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.pages.loginPage;

import java.time.Duration;

public class FunctionalityOfLoginPage {
    public WebDriver driver;
    public loginPage login;
    public WebDriverWait wait;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        login = new loginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    @Given("I am on the movie app login page")
    public void navigateToUrl(){
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @When("Website logo image is displayed,Heading text is \"Login\",Username label text is \"USERNAME\",Password label text is \"PASSWORD\"")
    public void loginPage(){
        Assert.assertTrue(login.isLogoDisplayed(),"Logo isn't display");
        Assert.assertEquals(login.isHeadTextDisplayed(),"Login");
        Assert.assertEquals(login.isUsernameLabelDisplayed(),"USERNAME");
        Assert.assertEquals(login.isPasswordLabelDisplayed(),"PASSWORD");

    }

    @And("\"Login\" button")
    public void loginButton(){
        Assert.assertEquals(login.isLoginTextVisible(),"Login");
        Assert.assertEquals(login.isLoginFontFamilyVisible(),"Roboto");
        Assert.assertEquals(login.isLoginBackGroundColorVisible(),"rgba(229, 9, 20, 1)");
    }

    @When("I enter valid username and password,I click on the login button")
    public  void invalidPassword(){
        Assert.assertTrue(login.areInputEmpty());
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
    }

    @Then("I should be redirected to the home page")
    public void isRedirect(){
        wait.until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://qamoviesapp.ccbp.tech/");

       }

    @When("I enter valid username and invalid password,I click on the login button")
    public  void validPassword(){
        Assert.assertTrue(login.areInputEmpty());
        login.login("rahul","rahul");
        login.clickOnLogButton();
    }

    @Then("Error will be  message appear")
    public void errorMsg(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        Assert.assertEquals(login.errorMessage(),"*username and password didn't match");

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
