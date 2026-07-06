package com;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.selenium.Amazon_home_page_object;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import com.selenium.LaunchBrowser;
//import com.google.common.io.Files;
import com.selenium.Handlebrowser;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;


import com.commonmethods.GuiMethods;
import com.commonmethods.utilities;

import java.io.File;
import java.io.IOException;
//import java.time.Duration;
//import org.testng.Assert;
import java.util.Map;

public class Instantiatebrowser {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws InterruptedException, IOException, InvalidFormatException {
                // Declare the maps to capture data
                Map<String, Object> locatorYamlData;
                Map<String, Object> locatorData;
                Map<String, Object> testYamlData;
                Map<String, Object> testData;

                // Extract Locator Data
                locatorYamlData = utilities.readYamlData("src/main/java/com/locators.yaml");
                locatorData = (Map<String, Object>) locatorYamlData.get("Amazon_Locators");
                String Category_dd_Locator = (String) locatorData.get("Category_dd_Xpath");
                String Category_options_Locator = (String) locatorData.get("Category_options_Xpath");
                String Search_tb_Locator = (String) locatorData.get("Search_tb_Xpath");
                String Suggestion_options_Locator = (String) locatorData.get("Suggestion_options_Xpath");
                String Search_button_Locator = (String) locatorData.get("Search_button_Xpath");
                String Search_validation_text_Locator = (String) locatorData.get("Search_validation_text_Xpath");

                // Extract Test Data
                testYamlData = utilities.readYamlData("src/main/java/com/Testdata.yaml");
                testData = (Map<String, Object>) testYamlData.get("Amazon_Test_Data");
                String App_URL = (String) testData.get("URL");
                String App_Product_Category = (String) testData.get("Product_Category");
                String App_Product_To_Search = (String) testData.get("Product_To_Search");
                String App_Product_Text_To_Select = (String) testData.get("Product_Text_To_Select");
                String App_Product_Search_Validation_text = (String) testData.get("Product_Search_Validation_text");

                WebDriver driver;
                File logDir = utilities.createFolder("AmazonSearchTestLogs");
                driver = Handlebrowser.handlewebdriver("Chrome");
                Amazon_home_page_object pageOb = new Amazon_home_page_object(driver, logDir);
                GuiMethods uimethods = new GuiMethods(driver, logDir);
                String logFile= "AmazonSearchProductTestCase";
                uimethods.createWordFile(logFile);

                // Launching Amazon website
                Handlebrowser.launchURL(driver, App_URL);
                uimethods.writeTextToWordFile(logFile,
                                "Amazon Home Page");
                uimethods.writeImageToWordFile(logFile);

                pageOb.SearchProduct(Category_dd_Locator,
                                Category_options_Locator, App_Product_Category,
                                Search_tb_Locator, App_Product_To_Search,
                                Suggestion_options_Locator,
                                App_Product_Text_To_Select, Search_button_Locator, logFile, 10);

                pageOb.validateSearch(Search_validation_text_Locator, App_Product_Search_Validation_text,
                                logFile, 10);

                // Quit the driver
                driver.quit();
        }

}
