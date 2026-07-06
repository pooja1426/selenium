package com.selenium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.commonmethods.GuiMethods;
import com.commonmethods.utilities;

public class Instantiatenewbrowser {
    WebDriver driver;
    File logDir = utilities.createFolder("AmazonSearchTestLogs");
    GuiMethods uimethods;

    @BeforeMethod
    public void setUp() throws IOException, InvalidFormatException {
        driver = Handlebrowser.handlewebdriver("Chrome");
        uimethods = new GuiMethods(driver, logDir);
        uimethods.createWordFile("AmazonSearchTestCase1");
        Handlebrowser.launchURL(driver, "https://www.amazon.in/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void amazonSearchTestCase1() throws IOException, InvalidFormatException {
        uimethods.validate_click("//label[@id=\"searchDropdownDescription\"]/../..", 5);
        uimethods.select_option_from_dropdown("//label[@id=\"searchDropdownDescription\"]/../..//option",
                "Books", 5);
        uimethods.validate_insert_text("//input[@id=\"twotabsearchtextbox\"]", "Quantum Physics", 10);
        uimethods.writeTextToWordFile("AmazonSearchTestCase1",
                "Search parameters entered: Category - Books, Search Text - Quantum Physics");
        uimethods.writeImageToWordFile("AmazonSearchTestCase1");
        uimethods.select_option_from_dropdown(
                "//div[contains(@id,\"sac-suggestion-row-\")]//span[@class=\"s-heavy\"]",
                "richard feynman", 5);
        uimethods.validate_click("//span[@aria-label=\"Go\"]", 5);

        String actualText = uimethods.validate_get_text("//span[@class=\"a-color-state a-text-bold\"]", 5);
        String expectedText = "\"quantum physics richard feynman\"";
        Assert.assertEquals(actualText, expectedText,
                "Search results page title does not match the expected text.");

           
        uimethods.writeTextToWordFile("AmazonSearchTestCase1",
                "Search Results: Category - Books, Search Text - Quantum Physics");
        uimethods.writeImageToWordFile("AmazonSearchTestCase1");
    }
}
