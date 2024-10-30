package org.pages;

import org.baseClass.basePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class searchPage extends basePage {

    @FindBy(id = "search") private WebElement searchInput;

    @FindBy(className = "search-empty-button")private WebElement searchEmptyButton;

    @FindBy(className = "search-button") private WebElement searchButton;

    @FindBy(css = "ul.search-movies-container a") private List<WebElement> movieResults;

    @FindBy(className = "not-found-search-paragraph") private WebElement errorMessage;

    @FindBy(className = "not-found-search-image") private WebElement errorImage;

    public searchPage(WebDriver driver) {
        super(driver);
    }

    public void enterSearchQuery(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickEmptySearchButton() {
        searchEmptyButton.click();
    }

    public int getMovieResultsCount() {
        return movieResults.size();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public boolean isErrorImageDisplayed() {
        return errorImage.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }
}
