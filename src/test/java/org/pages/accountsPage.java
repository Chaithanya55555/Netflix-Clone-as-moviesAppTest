package org.pages;
import org.baseClass.basePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class accountsPage extends basePage {
    // AccountPage.


    // Selectors
    private By accountHeading = By.className("account-heading");
    private By membershipHeading = By.className("membership-heading");
    private By username = By.className("membership-username");
    private By password = By.className("membership-password");
    private By planHeading = By.xpath("//div[@class ='plan-container']/p");
    private By planType = By.className("plan-paragraph");
    private By planDetails = By.className("plan-details");
    private By logoutButton = By.className("logout-button");

    public accountsPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public String getAccountHeadingText() {
        return driver.findElement(accountHeading).getText();
    }

    public String getMembershipHeadingText() {
        return driver.findElement(membershipHeading).getText();
    }

    public String getUsernameText() {
        return driver.findElement(username).getText();
    }

    public String getPasswordText() {
        return driver.findElement(password).getText();
    }

    public String getPlanHeadingText() {
        return driver.findElement(planHeading).getText();
    }

    public String getPlanTypeText() {
        return driver.findElement(planType).getText();
    }

    public String getPlanDetailsText() {
        return driver.findElement(planDetails).getText();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
