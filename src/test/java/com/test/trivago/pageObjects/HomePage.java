package com.test.trivago.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static com.test.trivago.pageObjects.BaseDriver.explicitWait;
import static com.test.trivago.pageObjects.BaseDriver.implicitWait;
import static com.test.trivago.pageObjects.BaseDriver.scrollintoviewElement;


public class HomePage {
   private WebDriver driver;
   //Locators
    @FindBy(how = How.XPATH, using = "//*[@class='search-icon']")
    private WebElement search;
    @FindBy(how = How.XPATH, using = "//*[@class='input search-input']")
    private WebElement searchData;
    @FindBy(how = How.XPATH, using = "//*[@class='search-results']")
    private WebElement searchResults;

   //Constructor
   public HomePage(WebDriver driver){
       this.driver=driver;
       //Initialise Elements
       PageFactory.initElements(driver, this);
   }
    public void searchEnabled(){
        //Click search
        explicitWait(driver,search);
        try {
            scrollintoviewElement(driver,search);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(search.isDisplayed()
                &&search.isEnabled()){

        }


    }

   public void clickSearchIcon(){
       //Click search
       explicitWait(driver,search);
       search.click();
       implicitWait(driver);

    }

    public void enterDataSearch(String data){
        try {
            scrollintoviewElement(driver,searchData);
        } catch (Exception e) {
            e.printStackTrace();
        }
       //Clear the field
        searchData.clear();
        //Click search
        searchData.sendKeys(data);
    }

    public void clickSearch(){
        //Click Enter
        searchData.sendKeys(Keys.RETURN);
    }
    public void validateResults(){
        //Click search
        String srchTitle= searchResults.findElement(By.xpath("//*[@class='section-title']")).getText().trim();
        String srchText= searchResults.findElement(By.tagName("span")).getText().trim();
        System.out.println("srchTitle:"+srchTitle+" srchText:"+srchText);

    }
}