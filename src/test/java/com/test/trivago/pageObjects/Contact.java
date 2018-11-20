package com.test.trivago.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.test.trivago.pageObjects.BaseDriver.scrollintoviewElement;
import static com.test.trivago.pageObjects.BaseDriver.switchWindow;


public class Contact {
    private WebDriver driver;
    //Locators
    @FindBy(how = How.XPATH, using = "//*[text()='Contact']")
    public WebElement contact;
    @FindBy(how = How.XPATH, using = "//textarea[@class='contact-msg']")
    public WebElement contactTextArea;
    @FindBy(how = How.XPATH, using = "//*[@class='contact-input']")
    public WebElement fullName;
    @FindBy(how = How.ID, using = "contact-email")
    public WebElement email;
    @FindBy(how = How.ID, using = "confirm")
    public WebElement confirm;
    @FindBy(how = How.XPATH, using = "//*[text()='Submit']")
    public WebElement submit;
    @FindBy(how = How.XPATH, using = "//*[@class='feedback']")
    public WebElement feedBack;

    //Constructor
    public Contact(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void contactVisible() {
        //Click search
        try {
            scrollintoviewElement(driver, contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickContact() {
        //Click search
        try {
            scrollintoviewElement(driver, contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
        contact.click();
        //Switch to the new window
        switchWindow(driver);
    }

    public void enterDataContactDetails(String message,
                                        String fullname,
                                        String mailID) {

        try {
            scrollintoviewElement(driver, contactTextArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Clear the field
        contactTextArea.clear();
        //Click search
        contactTextArea.sendKeys(message);
        //enter Fullname
        try {
            scrollintoviewElement(driver, fullName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        fullName.clear();
        fullName.sendKeys(fullname);
        //enter email
        try {
            scrollintoviewElement(driver, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        email.clear();
        email.sendKeys(mailID);
    }

    public void verifyFields() {
        try {
            scrollintoviewElement(driver, contactTextArea);
            scrollintoviewElement(driver, fullName);
            scrollintoviewElement(driver, email);
            scrollintoviewElement(driver, confirm);
            Assert.assertTrue((contactTextArea.isDisplayed() && contactTextArea.isEnabled())
                            && (fullName.isDisplayed() && fullName.isEnabled())
                            && (email.isDisplayed() && email.isEnabled())
                            && (confirm.isDisplayed() && confirm.isEnabled()),
                    "Text Area , Fullname ,email ID and Confirm checkBox");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clickConfirm() {
        //Click checkbox
        try {
            scrollintoviewElement(driver, confirm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        confirm.click();
    }

    public void clickSubmit() {
        try {
            scrollintoviewElement(driver, submit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Click checkbox
        submit.click();

    }

    //Verify message 'Message Sent Successfully! '
    public Boolean verifyMessage(String message) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,-250)", "");
        try {
            scrollintoviewElement(driver, feedBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedBack.getText().toUpperCase().trim().equals(message.toUpperCase().trim());
    }
}