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


public class ContactSteps {
    private WebDriver driver;
    private Contact contact;

    @Before
    public void beforeScenario(Scenario scenario) {
        //Open URL
        BaseDriver baseDriver = new BaseDriver();
        driver = baseDriver.getDriver();
        //PageObjects
        contact = new Contact(driver);
        if (!scenario.getName().equals("")) {
            Reporter.assignAuthor("Laddu shashavali");
            Reporter.addScenarioLog("Scenario: " + scenario.getName());
        }
    }

    @Given("^Contact is visible$")
    public void contact_is_visible() throws IOException {
        Reporter.addStepLog("Contact Link in the application");
        contact.contactVisible();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ContactVisible</a>");
    }

    @When("^I click Contact$")
    public void i_click_contact_page() throws IOException {
        Reporter.addStepLog("Click Contact Link in the application");
        contact.clickContact();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickContact</a>");
    }

    @When("^I enter (.+),(.+),(.+) data$")
    public void i_enter_and_data(String textarea, String fullname, String email) throws IOException {
        Reporter.addStepLog("Enter Fields in Contact Form in the application");
        contact.enterDataContactDetails(textarea, fullname, email);
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>EnterFields</a>");

    }

    @Then("^I see the appropriate fields$")
    public void i_see_the_appropriate_fields_contact() throws IOException {
        Reporter.addStepLog("Verify Fields in Contact Form in the application");
        contact.verifyFields();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>VerifyFields</a>");
    }

    @Then("^I see the success message (.+)$")
    public void i_see_the_success_message(String message) throws IOException {
        Reporter.addStepLog("Success message in the application");
        if (contact.verifyMessage(message)) {
            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SuccessMessage</a>");
        } else {
            Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>SuccessMessage</a>");
        }
    }

    @And("^I check the Confirm checkbox$")
    public void i_check_the_confirm_checkbox() throws IOException {
        Reporter.addStepLog("Click cpnfirm button in the application");
        contact.clickConfirm();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickConfirm</a>");
    }

    @And("^I click Submit button$")
    public void i_click_submit_button() throws IOException {
        Reporter.addStepLog("Click submit button in the application");
        contact.clickSubmit();
        Reporter.addStepLog("<a href='" + captureScreen(driver) + "'>ClickSubmit</a>");
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
