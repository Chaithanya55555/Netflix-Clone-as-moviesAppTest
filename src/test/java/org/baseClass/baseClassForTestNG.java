package org.baseClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class baseClassForTestNG {
    public WebDriver driver;
    public homePage homepage;
    public loginPage login;
    public WebDriverWait wait;
    public popularPage popularPage;
    public searchPage search;
    public accountsPage accountsPage;

    @Parameters({"br"})
    @BeforeMethod
    public void setup(String br){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--headless=new");
        switch (br.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();driver = new ChromeDriver(chromeOptions);break;
            case "edge":WebDriverManager.edgedriver().setup();driver = new EdgeDriver(edgeOptions);break;
            default: System.out.println("Invalid browser");return;
        }
        login = new loginPage(driver);
        homepage = new homePage(driver);
        popularPage = new popularPage(driver);
        search = new searchPage(driver);
        accountsPage = new accountsPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
