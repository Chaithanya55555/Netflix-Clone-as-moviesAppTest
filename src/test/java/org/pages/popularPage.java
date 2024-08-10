package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.List;
public class popularPage {


    private WebDriver driver;

    @FindBy(xpath = "//li[@class = 'movie-icon-item']") private List<WebElement> movieItems;

    @FindBy (xpath = "//a[text() ='Popular']") public WebElement popPage;

    @FindBy(xpath = "(//div[@class ='search-movies-container']//li)[last()]") public WebElement clickOnAnyMovieInPopularPage;

    @FindBy(className = "category-paragraph") public List<WebElement >NnumberOfGenres;

    public popularPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
