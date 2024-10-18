package org.pages;

import org.baseClass.basePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.ArrayList;
import java.util.List;
public class popularPage extends basePage {


    @FindBy(xpath = "//li[@class = 'movie-icon-item']") private List<WebElement> movieItems;

    @FindBy (xpath = "//a[text() ='Popular']") public WebElement popPage;

    @FindBy(xpath = "(//div[@class ='search-movies-container']//li)[last()]") public WebElement clickOnAnyMovieInPopularPage;

    @FindBy(className = "category-paragraph") public List<WebElement >NnumberOfGenres;

    public popularPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnHomePage(){
        popPage.click();
    }

    public boolean areMoviesDisplayed() {
        return !movieItems.isEmpty() && movieItems.stream().allMatch(WebElement::isDisplayed);
    }

    public void clickOnTheMovie(){
        clickOnAnyMovieInPopularPage.click();
    }

    public Boolean verifyGenresTitle(String genre){
        ArrayList<String> genres = new ArrayList<>();
        for (WebElement i : NnumberOfGenres){
            genres.add(i.getText());
        }
        return  genres.contains(genre);

    }




}
