package org.testNG;

import org.baseClass.baseClassForTestNG;
import org.baseUtilities.excelUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class login extends baseClassForTestNG {
    excelUtility xl;
    String sheetName;
    String path;

    public String dataProvider(int row , int col ) throws IOException {
         path = "C:\\Users\\HP\\IdeaProjects\\movieTest\\src\\test\\TestData\\Mini Project.xlsx";
         xl = new excelUtility(path);
         sheetName = "Sheet1";
        String loginData = xl.getCellData(sheetName,row,col);
        return loginData;
    }


    @Test(priority = 1, groups = "sanity")
    public  void UI() {
        try {


            logger.info("***** Validate the user interface *****");

            Assert.assertTrue(login.isLogoDisplayed(), "Logo isn't display");
            Assert.assertEquals(login.isHeadTextDisplayed(), "Login");
            Assert.assertEquals(login.isUsernameLabelDisplayed(), "USERNAME");
            Assert.assertEquals(login.isPasswordLabelDisplayed(), "PASSWORD");
            Assert.assertEquals(login.isLoginTextVisible(), "Login");
            Assert.assertEquals(login.isLoginFontFamilyVisible(), "Roboto");
            Assert.assertEquals(login.isLoginBackGroundColorVisible(), "rgba(229, 9, 20, 1)");
//            if (login.isLogoDisplayed()){
//                xl.setCellData(path,1,7,"Passed");
//                xl.fillGreenColor(path,1,7);
//            }else{
//                xl.setCellData(path,1,7,"Failed");
//                xl.fillRedColor(path,1,7);
//            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test(priority = 2, groups = "sanity")
    public void  successfullyLogin() throws IOException {
        try {
            logger.info("***** Navigate to login for successful login *****");

            logger.info("***** Checking the input boxes are empty  *****");
            Assert.assertTrue(login.areInputEmpty());
            logger.info("***** Getting input data from excel *****");
            String loginData = dataProvider(2,4 );
            String[] data = loginData.split(",");
            logger.info("***** Send the correct input data according to the inputs *****");
            login.login(data[0],data[1]);
            logger.info("***** Click on the login button *****");
            login.clickOnLogButton();
            logger.info("***** Wait for the home page *****");
            homepage.waits(5,"https://qamoviesapp.ccbp.tech/");
            logger.info("***** Validate the URL at home page *****");
            Assert.assertEquals(driver.getCurrentUrl(),"https://qamoviesapp.ccbp.tech/");

//            if (home.isLogoDisplayed()){
//                xl.setCellData(path,2,7,"Passed");
//                xl.fillGreenColor(path,2,7);
//            }else{
//                xl.setCellData(path,2,7,"Failed");
//                xl.fillRedColor(path,2,7);
//            }

        }catch (Exception e){
            System.out.println(e.getMessage());

        }
         }
    
    @Test(priority = 3)
    public void  unsuccessfullyLogin(){
        try {
            logger.info("***** Navigate to login for unsuccessful login *****");

            Assert.assertTrue(login.areInputEmpty());
            String loginData = dataProvider(3,4 );
            String[] data = loginData.split(",");
            logger.info("***** Send the incorrect input data according to the inputs *****");
            login.login(data[0],data[1]);
            login.clickOnLogButton();
            logger.info("***** Wait for the error msg is displayed or not *****");
            logger.info("***** Validate the error msg *****");
            Assert.assertEquals(login.errorMessage(),"*username and password didn't match");
//            if (login.errorMessage().equals("*username and password didn't match")){
//                xl.setCellData(path,3,7,"Passed");
//                xl.fillGreenColor(path,3,7);
//            }else{
//                xl.setCellData(path,3,7,"Failed");
//                xl.fillRedColor(path,3,7);
//            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
