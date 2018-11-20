package com.test.trivago.stepdefinitions;

import com.test.trivago.listener.Reporter;
import com.test.trivago.pageObjects.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.test.trivago.pageObjects.BaseDriver.captureScreen;

public class HomePageSearchSteps {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void beforeScenario(Scenario scenario) {
        //Open URL
        BaseDriver baseDriver = new BaseDriver();
        driver = baseDriver.getDriver();
        //PageObjects
        homePage = new HomePage(driver);

        if (!scenario.getName().equals("")) {
            Reporter.assignAuthor("Laddu shashavali");
            Reporter.addScenarioLog("Scenario: " + scenario.getName());
        }
    }

    @Given("^Search button is visible$")
    public void search_button_Visible() throws IOException {
        Reporter.addStepLog("Search Icon or button is Visible");
        homePage.searchEnabled();
        Reporter.addScreenCaptureFromPath(captureScreen(driver), "SearchIcon");
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SearchIcon</a>");
    }

    @When("^I see search icon enabled$")
    public void searchButtonEnabled() throws IOException {
        Reporter.addStepLog("Click on Search icon");
        homePage.clickSearchIcon();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSearchIcon</a>");
    }

    @Then("^I enter (.+) in the search field$")
    public void enterDataSearch(String data) throws IOException {
        Reporter.addStepLog("Enter data to search");
        homePage.enterDataSearch(data);
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>EnterData</a>");
    }

    @When("^I click on search$")
    public void searchClick() throws IOException {
        Reporter.addStepLog("Click on Search button to see the results");
        homePage.clickSearch();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSearchButton</a>");
    }

    @Then("^I see the search results$")
    public void searchResults() throws IOException {
        Reporter.addStepLog("Validating the results in the application");
        homePage.validateResults();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SearchResults</a>");
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>TestFail</a>");
                byte[] screenshotFail = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshotFail, "image/png");
            } else {
                byte[] screenshotPass = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshotPass, "image/png");
            }
            //Quit all Instances
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
