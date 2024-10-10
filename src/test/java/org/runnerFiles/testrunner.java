package org.runnerFiles;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
// src/test/java/runners/TestRunner.java

@CucumberOptions(
        features = {"src/test/resources/feature/LoginPage.feature","src/test/resources/feature/HomePage.feature","src/test/resources/feature/PopularPage.feature","src/test/resources/feature/SearchPage.feature","src/test/resources/feature/AccountPage.feature"},
        plugin = {"pretty","html:target/CucumberReports/CucumberReport.html"}
)
public class testrunner extends AbstractTestNGCucumberTests {
}
