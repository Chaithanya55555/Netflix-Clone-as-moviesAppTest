package org.testNG;


import com.beust.jcommander.Parameter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.loginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class login {
    public WebDriver driver;
    public loginPage login;
    public WebDriverWait wait;

    @Parameters({"br"})
    @BeforeMethod
    public void setup(String br ){
        switch (br.toLowerCase()){
            case "chrome":WebDriverManager.chromedriver().setup();driver = new ChromeDriver();break;
            case "edge":WebDriverManager.edgedriver().setup();driver = new EdgeDriver();break;
            default: System.out.println("Invalid browser");return;
        }
        driver = new ChromeDriver();
        login = new loginPage(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public  void UI() {
        driver.get("https://qamoviesapp.ccbp.tech");
        Assert.assertTrue(login.isLogoDisplayed(),"Logo isn't display");
        Assert.assertEquals(login.isHeadTextDisplayed(),"Login");
        Assert.assertEquals(login.isUsernameLabelDisplayed(),"USERNAME");
        Assert.assertEquals(login.isPasswordLabelDisplayed(),"PASSWORD");
        Assert.assertEquals(login.isLoginTextVisible(),"Login");
        Assert.assertEquals(login.isLoginFontFamilyVisible(),"Roboto");
        Assert.assertEquals(login.isLoginBackGroundColorVisible(),"rgba(229, 9, 20, 1)");

    }

    @Test(priority = 2)
    public void  successfullLogin(){
        driver.get("https://qamoviesapp.ccbp.tech");
        Assert.assertTrue(login.areInputEmpty());
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
        wait.until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://qamoviesapp.ccbp.tech/");
    }
    
    @Test(priority = 3)
    public void  unsuccessfullLogin(){
        driver.get("https://qamoviesapp.ccbp.tech");
        Assert.assertTrue(login.areInputEmpty());
        login.login("rahul","rahul");
        login.clickOnLogButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        Assert.assertEquals(login.errorMessage(),"*username and password didn't match");
    }

    @AfterMethod
    public void tearDown(){
       if (driver != null) {
           driver.quit();
       }
    }
}
