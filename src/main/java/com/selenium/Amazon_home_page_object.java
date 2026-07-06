package com.selenium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.commonmethods.GuiMethods;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Amazon_home_page_object extends GuiMethods {
    WebDriver driver;
    File logDir;

    public Amazon_home_page_object(WebDriver driver, File logDir) {
        super(driver, logDir);
        this.driver = driver;
        this.logDir = logDir;
    }
    
    public void SearchProduct(String dropDownlocator, String categoryoptionsLocator, String category,
            String searchBoxLocator, String searchText, String optionsLocator,  String optionText,
            String searchButton,String logFile, int timeout)
            throws InvalidFormatException, IOException {
        validate_click_js(dropDownlocator, timeout);
        select_option_from_dropdown(categoryoptionsLocator,
                category, timeout);
        validate_insert_text_uc(searchBoxLocator, searchText, timeout);
        writeTextToWordFile(logFile,
                driver.getTitle());
        writeImageToWordFile(logFile);
        select_option_from_dropdown(
                optionsLocator, optionText, timeout);
        validate_click(searchButton, timeout);
    }

    public void validateSearch(String postSearchTextLocator, String expectedText, String fileName, int timeout)
            throws IOException, InvalidFormatException {
        String actualText = validate_get_text(postSearchTextLocator, 5);
        Assert.assertEquals(actualText, expectedText,
                "Search results page title does not match the expected text.");
        writeTextToWordFile("AmazonSearchTestCase1",
                driver.getTitle());
        writeImageToWordFile("AmazonSearchTestCase1");
    }

}




