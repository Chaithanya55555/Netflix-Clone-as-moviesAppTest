package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
public class homePage {
    // src/test/java/pageobjects/Homepage.java
    public Boolean isDisplay;
    public WebDriver driver;

    @FindBy(xpath = "//div[@class = 'home-bottom-container']//div[1]//h1")
    public WebElement mainHeading;

    @FindBy(xpath = "//div[@class = 'home-bottom-container']//div[2]//h1")
    public WebElement sideHeading;

    @FindBy(className = "home-movie-play-button")
    public WebElement playButton;

    @FindBy(xpath = "(//div[@class = 'slick-list'])[position() = 1]//img")
    public List<WebElement> trendingMovies;

    @FindBy(xpath = "(//div[@class = 'slick-list'])[position() = 2]//img")
    public List<WebElement> originalMovies;


    @FindBy(css = "div.footer-icons-container svg")
    public List<WebElement> contactUsSection;

    @FindBy(className = "contact-us-paragraph")
    public WebElement contactForm;

    @FindBy(className = "website-logo")
    public WebElement websiteLogo;

    @FindBy(xpath = "//ul[@class = 'nav-menu-list']//li[1]//a")
    public WebElement homeLink;

    @FindBy(xpath = "//ul[@class = 'nav-menu-list']//li[2]//a")
    public WebElement popularLink;

    @FindBy(xpath = "//button[ @class = 'avatar-button']//img")
    public WebElement accountLink;

    @FindBy(xpath = "//ul[@class = 'nav-menu-list']//li")
    public List<WebElement> navbarElements;

    @FindBy(xpath = "(//div[@class = 'home-bottom-container']/div[2]//a[@class = 'link-item'])[3]" )
    public  WebElement Movie;

    @FindBy(className = "movie-title")
    public WebElement movieTitle;

    @FindBy(css = ".movie-overview")
    public WebElement movieDescription;

    @FindBy (className = "watch-time")
    public  WebElement watchTime;

    @FindBy(css = ".sensor-rating")
    public WebElement sensorMovieRating;
     
    @FindBy(className = "release-year")
    public WebElement releaseYear;

    @FindBy(xpath = "//p[@class = 'movie-overview']/following::button")
    public WebElement playMovieButton;

    private By genresHeading = By.className("genres-heading");
    private By genreParagraph = By.className("category-paragraph");
    private By audioHeading = By.className("audio-heading");
    private By audioListItem = By.className("audio-list-item");
    private By ratingCountHeading = By.className("rating-heading");
    private By ratingCountParagraph = By.xpath("//div[contains(@class, 'rating-category')]/p[1]");
    private By ratingAverageHeading = By.className("rating-average-heading");
    private By ratingAverageParagraph = By.xpath("//div[contains(@class, 'rating-category')]/p[2]");
    private By budgetHeading = By.className("budget-heading");
    private By budgetParagraph = By.xpath("//div[contains(@class, 'budget-category')]/p[1]");
    private By releaseDateHeading = By.className("release-date-heading");
    private By releaseDateParagraph = By.xpath("//div[contains(@class, 'budget-category')]/p[2]");

    public homePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getGenre() {
        return driver.findElement(genreParagraph).getText();
    }

    public String getGenreHeading() {
        return driver.findElement(genresHeading).getText();
    }

    public String getAudioHeading() {
        return driver.findElement(audioHeading).getText();
    }

    public String getRatingCountHeading() {
        return driver.findElement(ratingCountHeading).getText();
    }

    public String getAverageRatingHeading() {
        return driver.findElement(ratingAverageHeading).getText();
    }

    public String getBudgetHeading() {
        return driver.findElement(budgetHeading).getText();
    }

    public String getReleaseDateHeading() {
        return driver.findElement(releaseDateHeading).getText();
    }

    public String getAudioAvailable() {
        return driver.findElement(audioListItem).getText();
    }

    public String getRatingCount() {
        return driver.findElement(ratingCountParagraph).getText();
    }

    public String getRatingAverage() {
        return driver.findElement(ratingAverageParagraph).getText();
    }

    public String getBudget() {
        return driver.findElement(budgetParagraph).getText();
    }

    public String getReleaseDate() {
        return driver.findElement(releaseDateParagraph).getText();
    }

    public String getMovieTitle() {
        return movieTitle.getText();
    }

    public String getMovieDescription() {
        return movieDescription.getText();
    }

    public String getWatchTime() {
        return watchTime.getText();
    }

    public String getMovieRating() {
        return sensorMovieRating.getText();
    }

    public String releaseYear() {
        return releaseYear.getText();
    }

    public boolean isPlayButtDisplayed() {
        return playButton.isDisplayed();
    }

    public void clickOnMovie() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(Movie));
        Movie.click();
    }

    public String getMainHeading() {
        return mainHeading.getText();
    }

    public String getSideHeading() {
        return sideHeading.getText();
    }

    public boolean isPlayButtonDisplayed() {
        return playMovieButton.isDisplayed();
    }

    public boolean arePopularMoviesDisplayed() {
        return !trendingMovies.isEmpty();
    }

    public boolean areTopRatedMoviesDisplayed() {
        return !originalMovies.isEmpty();
    }

    public boolean verifyTrendingMoviesAreDisplayedInCorresponding() {
        ArrayList<String> trendMovie = new ArrayList<>();
        trendMovie.add("no-time-to-die-movie-poster");
        trendMovie.add("shang-chi-and-the-legend-of-the-ten-rings-movie-poster");
        trendMovie.add("dune-movie-poster");
        trendMovie.add("the-suicide-squad-movie-poster");
        trendMovie.add("venom-let-there-be-carnage-movie-poster");
        trendMovie.add("eternals-movie-poster");
        trendMovie.add("army-of-thieves-movie-poster");
        trendMovie.add("the-harder-they-fall-movie-poster");
        trendMovie.add("halloween-kills-movie-poster");
        trendMovie.add("twilight-movie-poster");
        for (String i : trendMovie) {
            isDisplay = driver.findElement(By.xpath("//img[contains(src ," + i + ")]")).isDisplayed();

        }
        return isDisplay;
    }

    public boolean verifyOriginalMoviesAreDisplayedInCorresponding() {
        ArrayList<String> trendMovie = new ArrayList<>();
        trendMovie.add("grindhouse-movie-poster");
        trendMovie.add("clifford-the-big-red-dog-movie-poster");
        trendMovie.add("titanic-movie-poster");
        trendMovie.add("avatar-theatrical-movie-poster");
        trendMovie.add("a-few-good-men-movie-poster");
        trendMovie.add("squid-game-south-korean-movie-poster");
        trendMovie.add("the-pursuit-of-happyness-movie-poster");
        trendMovie.add("king-richard-movie-poster");
        trendMovie.add("dexter-new-blood-movie-poster");
        trendMovie.add("riverdale-movie-poster");
        for (String i : trendMovie) {
            isDisplay = driver.findElement(By.xpath("//img[contains(src ," + i + ")]")).isDisplayed();

        }
        return isDisplay;
    }

    public boolean isContactUsSectionPresent() {
        boolean isDisplay = false;
        for (WebElement i : contactUsSection) {
            if (i.isDisplayed()) {
                isDisplay = true;
            }
        }
        return isDisplay;
    }

    public boolean isContactFormFunctional() {
        return contactForm.isDisplayed();

    }

    public String getText() {
        return contactForm.getText();

    }

    public void waits(int secs, String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public boolean isLogoDisplayed() {
        return websiteLogo.isDisplayed();
    }

    public void clickHomeLink() {
        homeLink.click();
    }

    public void clickPopularLink() {
        popularLink.click();
    }

    public void clickAccountLink() {
        accountLink.click();
    }

    public List<WebElement> getNavbarElements() {
        return navbarElements;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
