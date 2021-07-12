package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"C:\\IO\\APITest\\src\\test\\resources\\Features"},
plugin = "json:target/jsonReports/cucumber-report.json",
dryRun = false,
glue = {"stepDefinations"},
tags = "")


public class RunnerTest {
}
