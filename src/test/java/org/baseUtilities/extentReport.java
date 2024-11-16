package org.baseUtilities;


import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.baseClass.baseClassForTestNG;

import org.testng.ITestListener;


import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class extentReport  implements ITestListener {
//    public static void main(String[] args) throws IOException {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//
//
//
//        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
//
//        String testReportName = "Test Report " + timeStamp + ".html";
//        String path = "reports\\"+ testReportName;
//        ExtentSparkReporter spark = new ExtentSparkReporter(path);
//        ExtentReports extent = new ExtentReports();
//        extent.attachReporter(spark);
//        extent.setSystemInfo("OS",System.getProperty("os.name"));
//        extent.setSystemInfo("Browser", cap.getBrowserName() + " " + cap.getBrowserVersion());
//        extent.setSystemInfo("Host", System.getProperty("os.name"));
//
//        Throwable th = new RuntimeException();
//        extent.createTest("Test1").assignAuthor("Shiva").assignCategory("Base").assignDevice("Chrome 130").info(MarkupHelper.createLabel("Base",ExtentColor.RED)).info(MediaEntityBuilder.createScreenCaptureFromPath("D:\\Pictures\\wallpaperflare.com_wallpaper.jpg").build());
//        extent.createTest("Test2").assignAuthor("Shiva").assignCategory("Base").assignDevice("Chrome 130").log(Status.INFO,";ksfjg").addScreenCaptureFromPath("D:\\Pictures\\wallpaperflare.com_wallpaper.jpg");
//        extent.flush();
//
//        Desktop.getDesktop().browse(new File(path).toURI());
//    }


    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    String path;


    @Override
    public void onStart(ITestContext testContext)  {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        String repName="Test-Report "+timeStamp+".html";
        path = "reports\\"+ repName;

        sparkReporter = new ExtentSparkReporter(path);//specify location of the report

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Environemnt","QA");
        extent.setSystemInfo("user","Chaithanya");

        sparkReporter.config().setDocumentTitle("MoviesApp Test Project"); // Tile of report
        sparkReporter.config().setReportName("Functional Test Automation Report"); // name of the report

    }
    @Override
    public void onTestSuccess(ITestResult tr) {
        extent.createTest(tr.getName()).log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
    }


    @Override
    public void onTestFailure(ITestResult tr)
    {
        ExtentTest ex = extent.createTest(tr.getName());
        String screenshotPath = null;
        try {
            screenshotPath = new baseClassForTestNG().captureScreen(tr.getName());
            ex.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
            ex.addScreenCaptureFromPath(screenshotPath);
            // send the passed information to the report with GREEN color highlighted


        } catch (Exception e) {
            ex.fail(e);
        }
    }
    @Override
    public void onTestSkipped(ITestResult tr)
    {
        extent.createTest(tr.getName()) // create new entry in th report
         .log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }
    @Override
    public void onFinish(ITestContext testContext)  {
        extent.flush();
        try {
            Desktop.getDesktop().browse(new File(path).toURI());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
