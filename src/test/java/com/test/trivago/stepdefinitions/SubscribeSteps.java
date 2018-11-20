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


public class SubscribeSteps {
    private WebDriver driver;
    private Subscribe subscribe;

    @Before
    public void beforeScenario(Scenario scenario) {
        //Open URL
        BaseDriver baseDriver = new BaseDriver();
        driver = baseDriver.getDriver();
        //PageObjects
        subscribe = new Subscribe(driver);

        if (!scenario.getName().equals("")) {
            Reporter.assignAuthor("Laddu shashavali");
            Reporter.addScenarioLog("Scenario: " + scenario.getName());
        }
    }

    @Given("^Subscribe is visible$")
    public void subscribe_is_visible() throws IOException {
        Reporter.addStepLog("Subscribe is visible in the application");
        subscribe.fieldsVisible();
        Reporter.addScreenCaptureFromPath(captureScreen(driver), "SubscribeVisible");

    }

    @When("^I see the appropriate fields in subscribe$")
    public void i_see_the_appropriate_fields_in_subscribe() throws IOException {
        Reporter.addStepLog("Appropriate fields are visible in the application");
        subscribe.fieldsVisible();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>FieldSubscribe</a>");
    }

    @When("^I click Submit button in Subscribe$")
    public void i_click_submit_button_subscribe() throws IOException {
        Reporter.addStepLog("Click submit button in the application");
        subscribe.clickSubmit();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSubmit</a>");
    }

    @And("^I click Confirm$")
    public void i_click_confirm_subscribe() throws IOException {
        Reporter.addStepLog("Subscribe is visible in the application");
        subscribe.clickConfirm();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SubscribeVisible</a>");
    }

    @Then("^I verify success message (.+) in Subscribe$")
    public void i_see_the_success_message_subscribe(String message) throws IOException {
        Reporter.addStepLog("See the success message in the application");
        if (subscribe.verifyMessage(message)) {
            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SuccessMessage</a>");
        } else {
            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SuccessMessage</a>");
        }
    }

    @Then("^I enter email (.+) data$")
    public void i_enter_data(String email) throws IOException {
        Reporter.addStepLog("Eneter email in the application");
        subscribe.enteremail(email);
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>Enteremail</a>");
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
