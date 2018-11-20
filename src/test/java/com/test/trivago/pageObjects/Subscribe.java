package com.test.trivago.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.test.trivago.pageObjects.BaseDriver.scrollintoviewElement;

public class Subscribe {
    private WebDriver driver;
    //Locators

    @FindBy(how = How.XPATH, using = "//*[@class='alert-error et-confirm-error']")
    public WebElement confirmAttribute;
    @FindBy(how = How.XPATH, using = "//*[@class='newletter-opt-in-checkbox et-confirm']")
    public WebElement confirm;
    @FindBy(how = How.NAME, using = "email")
    public WebElement email;
    @FindBy(how = How.XPATH, using = "//*[@class='newsletter-submit']/button[@class='submit']")
    public WebElement submit;
    @FindBy(how = How.XPATH, using = "//*[@class='submitted h1']")
    public WebElement feedbackMsg;

    //Constructor
    public Subscribe(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void fieldsVisible() {
        try {
            scrollintoviewElement(driver, confirm);
            scrollintoviewElement(driver, email);
            Assert.assertTrue((confirm.isDisplayed() && confirm.isEnabled())
                            && (email.isDisplayed() && email.isEnabled()),
                    "Confirm button , email Button");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickConfirm() {
        //Click confirm
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", confirm);
            executor.executeScript("arguments[0].click();", confirm);
        } catch (Exception e) {
            Actions builder = new Actions(driver);
            builder.moveToElement(confirm).click().build().perform();
        }

    }
    //Enter email
    public void enteremail(String mail) {
        email.clear();
        //Click search
        email.sendKeys(mail.trim());
    }
    //Click submit
    public void clickSubmit() {

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            scrollintoviewElement(driver, submit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.executeScript("arguments[0].click();", submit);

    }

    //Verify message 'You are now checked-in!'
    public Boolean verifyMessage(String message) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,-700)", "");
        try {
            scrollintoviewElement(driver, feedbackMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackMsg.getText().toUpperCase().trim().equals(message.toUpperCase().trim());
    }
}