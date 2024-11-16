package org.testNG;

import org.baseClass.baseClassForTestNG;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class account extends baseClassForTestNG {
    private final String baseUrl = "https://qamoviesapp.ccbp.tech/login";

    @Test(priority = 1)
    public void UIOfAccountPage(){
        homepage.waits(5,baseUrl);
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/");
        homepage.clickAccountLink();
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/account");
        try{
            Assert.assertEquals( accountsPage.getAccountHeadingText(),"Account");
            Assert.assertEquals( accountsPage.getMembershipHeadingText(),"Member ship");
            Assert.assertEquals( accountsPage.getUsernameText(),"User name : rahul");
            Assert.assertEquals( accountsPage.getPasswordText(),"Password : **********");
            Assert.assertEquals( accountsPage.getPlanHeadingText(),"Plan details");
            Assert.assertEquals( accountsPage.getPlanTypeText(),"Premium");
            Assert.assertEquals( accountsPage.getPlanDetailsText(),"Ultra HD");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 2,groups = "sanity")
    public void logout(){
        homepage.waits(5,baseUrl);
        login.login("rahul","rahul@2021");
        login.clickOnLogButton();
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/login");
        homepage.clickAccountLink();
        homepage.waits(5,"https://qamoviesapp.ccbp.tech/account");
        accountsPage.clickLogoutButton();
        String expectedLogoutUrl = "https://qamoviesapp.ccbp.tech/login";
        Assert.assertEquals(expectedLogoutUrl, driver.getCurrentUrl());

    }
}
