package org.baseUtilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import org.baseClass.baseClassForTestNG;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class AIcode  extends TestListenerAdapter{

        public ExtentSparkReporter sparkReporter;
        public ExtentReports extent;
        public ExtentTest test;
        String reportPath;
        baseClassForTestNG screenshot;
    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Interactive-Test-Report-" + timeStamp + ".html";
        reportPath = "reports\\" + reportName;

        // Initialize ExtentSparkReporter with path
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("MoviesApp Test Project - Interactive Report");
        sparkReporter.config().setReportName("Functional Test Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);

        // Enabling view order and filter for a more interactive report
        sparkReporter.config().enableOfflineMode(true);  // Helpful if sharing the report

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add detailed system info
        extent.setSystemInfo("Host Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Chaithanya");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Build Number", "v1.0.4");
        extent.setSystemInfo("Test Execution Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    @Override
    public void onTestStart(ITestResult tr) {
        // Start a new test node in the report
        test = extent.createTest(tr.getMethod().getMethodName())
                .assignCategory(tr.getMethod().getGroups()) // Grouping tests
                .assignAuthor("QA Team");

        // Add a description to make the report more informative
        String description = tr.getMethod().getDescription();
        if (description != null && !description.isEmpty()) {
            test.info("Test Description: " + description);
        }

        // Adding groups (categories) based on test groups for easier filtering
        String[] groups = tr.getMethod().getGroups();
        for (String group : groups) {
            test.assignCategory(group);  // Adds grouping based on test category
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        test.pass(MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
        test.pass("Test passed successfully.");
        addTestDuration(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        String screenshotPath = null;
        try {
            screenshotPath = screenshot.captureScreen(tr.getName());
            test.fail(MarkupHelper.createLabel(tr.getName(), ExtentColor.RED))
                    .fail("Screenshot on failure:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            test.fail("Exception: " + tr.getThrowable().getMessage());  // Log exception details
        } catch (IOException e) {
            e.printStackTrace();
        }
        addTestDuration(tr);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        test.skip(MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
        test.skip("Test skipped.");
        addTestDuration(tr);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        // Summary of results for quick overview
        int passed = testContext.getPassedTests().size();
        int failed = testContext.getFailedTests().size();
        int skipped = testContext.getSkippedTests().size();

        ExtentTest summary = extent.createTest("Test Summary")
                .info("Total Passed: " + passed)
                .info("Total Failed: " + failed)
                .info("Total Skipped: " + skipped);

        // Adding summary categories for easy report filtering
        summary.assignCategory("Summary");

        // Flush and open report automatically
        extent.flush();
        try {
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to add duration for each test
    private void addTestDuration(ITestResult tr) {
        long duration = tr.getEndMillis() - tr.getStartMillis();
        test.info("Test Duration: " + duration / 1000.0 + " seconds");
    }

    }
