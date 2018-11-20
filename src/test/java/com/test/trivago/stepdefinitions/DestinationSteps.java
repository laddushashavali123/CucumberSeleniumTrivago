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

public class DestinationSteps {
    private WebDriver driver;
    private Destination destination;

    @Before
    public void beforeScenario(Scenario scenario) {
        //Open URL
        BaseDriver baseDriver = new BaseDriver();
        driver = baseDriver.getDriver();
        //PageObjects
        destination = new Destination(driver);
        if (!scenario.getName().equals("")) {
            Reporter.assignAuthor("Laddu shashavali");
            Reporter.addScenarioLog("Scenario: " + scenario.getName());
        }
    }

    @Given("^topleftIcon is visible$")
    public void toplefticon_is_visible() throws IOException {
        Reporter.addStepLog("Top left Icon in the application");
        destination.topLeftIconVisible();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>TopleftIcon</a>");
    }

    @When("^I click topleftIcon$")
    public void i_click_toplefticon() throws IOException {
        Reporter.addStepLog("Click Topleft Icon in the application");
        destination.clickIcon();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickIcon</a>");
    }

    @When("^I click DestinationMenu item$")
    public void i_click_destinationmenu_item() throws IOException {
        Reporter.addStepLog("Destination Menu Item is clicked in the application");
        destination.clickDestinationMenu();
        destination.clickMenuItem();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickMenuItem</a>");
    }

    @When("^I see DestinationSubMenu item$")
    public void i_see_destinationsubmenu_item() throws IOException {
        Reporter.addStepLog("Destination SubMenu Item is visible in the application");
        destination.viewSubMenuItem();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSubMenuItem</a>");
    }

    @When("^I click DestinationSubMenu item$")
    public void i_click_destinationsubmenu_item() throws IOException {
        Reporter.addStepLog("Destination SubMenu Item is clicked in the application");
        destination.clickSubMenuItem();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSubMenuItem</a>");
    }

    @Then("^I see Destination menu$")
    public void i_see_destination_menu() throws IOException {
        Reporter.addStepLog("Destination Menu in the application");
        destination.verifyMenu();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>VerifyMenu</a>");
    }

    @Then("^I see destination results$")
    public void i_see_destination_results_destination() throws IOException {
        Reporter.addStepLog("Destination results in the application");
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>DestinationResults</a>");
        destination.seeResults();
        Reporter.addScreenCaptureFromPath(captureScreen(driver), "DestinationResults");
    }

    @And("^I verify destination results$")
    public void i_verify_destination_results() throws IOException {
        Reporter.addStepLog("Validate Destination results in the application");
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ValidateDestinationResults</a>");
        destination.validateResults();
        Reporter.addScreenCaptureFromPath(captureScreen(driver), "ValidateDestinationResults");
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
