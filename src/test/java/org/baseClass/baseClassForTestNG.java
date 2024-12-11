package org.baseClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.pages.*;
import org.testng.annotations.*;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class baseClassForTestNG {
    
    public static WebDriver driver;
    public homePage homepage;
    public loginPage login;
    public popularPage popularPage;
    public searchPage search;
    public accountsPage accountsPage;
    public static Logger logger;
    public Properties p;

    @Parameters({"br","os"})
    @BeforeMethod
    public void setup(String br , String os) throws IOException {
        FileReader file = new FileReader("C:\\Users\\HP\\IdeaProjects\\movieTest\\src\\test\\resources\\configure.properties");
        p = new Properties();
        p.load(file);

        logger =  LogManager.getLogger(this.getClass());

        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--headless=new");
        EdgeOptions edgeOption = new EdgeOptions();
        edgeOption.addArguments("--headless=new");


        if (p.getProperty("execution_ev").equals("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();
            if (os.equalsIgnoreCase("window")) {
                cap.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                cap.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")) {
                cap.setPlatform(Platform.LINUX);
            }else {
                System.out.println("No matching OS");
                return;
            }

            switch (br.toLowerCase()) {
                case "chrome": cap.setBrowserName("chrome");break;
                case "edge": cap.setBrowserName("MicrosoftEdge");break;
                case "firefox": cap.setBrowserName("firefox");break;
                case "safari": cap.setBrowserName("safari");
                default: System.out.println("Invalid browser");return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        }


        if (p.getProperty("execution_ev").equals("local")) {
        switch (br.toLowerCase()){
            case "chrome":WebDriverManager.chromedriver().setup();driver = new ChromeDriver(chromeOption);break;
            case "edge":WebDriverManager.edgedriver().setup();driver = new EdgeDriver(edgeOption);break;
            default: System.out.println("Invalid browser");return;
            }
        }


        driver.manage().window().maximize();
        login = new loginPage(driver);
        homepage = new homePage(driver);
        popularPage = new popularPage(driver);
        search = new searchPage(driver);
        accountsPage = new accountsPage(driver);
        driver.get(p.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

    public String captureScreen(String tname)  {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());

        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String targetFilePath= "C:\\Users\\HP\\IdeaProjects\\movieTest\\screenshots\\" + tname + "_" + timeStamp+ ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }

}
