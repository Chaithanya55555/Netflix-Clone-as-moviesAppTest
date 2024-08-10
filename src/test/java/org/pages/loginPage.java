package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
    public WebDriver driver;

    @FindBy (className = "login-website-logo") WebElement logo;

    @FindBy (className = "sign-in-heading") WebElement headText;

    @FindBy(how = How.XPATH, using = "//div[@class = 'input-container'][1]//label") WebElement usernameLabel;

    @FindBy(how = How.XPATH,using = "//div[@class = 'input-container'][2]//label") WebElement passwordLabel;

    @FindBy(tagName = "button") WebElement loginButton;

    @FindBy(id = "usernameInput") WebElement userName;

    @FindBy(id = "passwordInput") WebElement password;

    @FindBy(className = "error-message") WebElement errorMessage;

    public loginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver , this);
    }

    public Boolean isLogoDisplayed(){
        return logo.isDisplayed();
    }

    public String isHeadTextDisplayed(){
        return headText.getText();
    }

    public String isUsernameLabelDisplayed(){
        return usernameLabel.getText();
    }

    public String isPasswordLabelDisplayed(){
        return passwordLabel.getText();
    }

    public String isLoginTextVisible(){
        String text = loginButton.getText();
        return text;
    }

    public String isLoginFontFamilyVisible(){
        String fontFamily = loginButton.getCssValue("font-family");
        return fontFamily ;
    }

    public String isLoginBackGroundColorVisible(){
        String backGroundColor = loginButton.getCssValue("background-color");
        return backGroundColor ;
    }

    public Boolean areInputEmpty(){
        Boolean emptyUser = userName.getAttribute("value").isEmpty();
        Boolean emptyPassword = password.getAttribute("value").isEmpty();
        return emptyUser && emptyPassword;
    }

    public void login(String user , String pass){
        userName.sendKeys(user);
        password.sendKeys(pass);
    }

    public void clickOnLogButton(){
        loginButton.click();
    }

    public String errorMessage(){
        return errorMessage.getText();
    }
}
