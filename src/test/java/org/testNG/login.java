package org.testNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.excelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.homePage;
import org.pages.loginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class login {
    WebDriver driver;
    loginPage login;
    WebDriverWait wait;
    excelUtility xl;
    String sheetName;
    homePage home;
    String path;
    @Parameters({"br"})
    @BeforeMethod
    public void setup(String br){
        switch (br.toLowerCase()){
            case "chrome":WebDriverManager.chromedriver().setup();driver = new ChromeDriver();break;
            case "edge":WebDriverManager.edgedriver().setup();driver = new EdgeDriver();break;
            default: System.out.println("Invalid browser");return;
        }
        login = new loginPage(driver);
        home = new homePage(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String dataProvider(int row , int col ) throws IOException {
         path = "C:\\Users\\HP\\IdeaProjects\\movieTest\\src\\test\\TestData\\Mini Project.xlsx";
         xl = new excelUtility(path);
         sheetName = "Sheet1";
        String loginData = xl.getCellData(sheetName,row,col);
        return loginData;
    }


    @Test(priority = 1)
    public  void UI() throws Exception{
            driver.get("https://qamoviesapp.ccbp.tech");
            Assert.assertTrue(login.isLogoDisplayed(),"Logo isn't display");
            Assert.assertEquals(login.isHeadTextDisplayed(),"Login");
            Assert.assertEquals(login.isUsernameLabelDisplayed(),"USERNAME");
            Assert.assertEquals(login.isPasswordLabelDisplayed(),"PASSWORD");
            Assert.assertEquals(login.isLoginTextVisible(),"Login");
            Assert.assertEquals(login.isLoginFontFamilyVisible(),"Roboto");
            Assert.assertEquals(login.isLoginBackGroundColorVisible(),"rgba(229, 9, 20, 1)");
//            if (login.isLogoDisplayed()){
//                xl.setCellData(path,1,7,"Passed");
//                xl.fillGreenColor(path,1,7);
//            }else{
//                xl.setCellData(path,1,7,"Failed");
//                xl.fillRedColor(path,1,7);
//            }


    }

    @Test(priority = 2)
    public void  successfullyLogin() throws IOException {
        try {
            driver.get("https://qamoviesapp.ccbp.tech");
            Assert.assertTrue(login.areInputEmpty());
            String loginData = dataProvider(2,4 );
            String[] data = loginData.split(",");
            System.out.println(loginData);
            login.login(data[0],data[1]);
            login.clickOnLogButton();
            wait.until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/"));
            Assert.assertEquals(driver.getCurrentUrl(),"https://qamoviesapp.ccbp.tech/");
//            if (home.isLogoDisplayed()){
//                xl.setCellData(path,2,7,"Passed");
//                xl.fillGreenColor(path,2,7);
//            }else{
//                xl.setCellData(path,2,7,"Failed");
//                xl.fillRedColor(path,2,7);
//            }

        }catch (Exception e){
            System.out.println();

        }
         }
    
    @Test(priority = 3)
    public void  unsuccessfullyLogin(){
        try {
            driver.get("https://qamoviesapp.ccbp.tech");
            Assert.assertTrue(login.areInputEmpty());
            String loginData = dataProvider(3,4 );
            String[] data = loginData.split(",");
            System.out.println(loginData);
            login.login(data[0],data[1]);
            login.clickOnLogButton();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
            Assert.assertEquals(login.errorMessage(),"*username and password didn't match");
//            if (login.errorMessage().equals("*username and password didn't match")){
//                xl.setCellData(path,3,7,"Passed");
//                xl.fillGreenColor(path,3,7);
//            }else{
//                xl.setCellData(path,3,7,"Failed");
//                xl.fillRedColor(path,3,7);
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @AfterMethod
    public void tearDown(){
       if (driver != null) {
           driver.quit();
       }
    }
}
