package com;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.commonmethods.utilities;
import com.selenium.Amazon_home_page_object;
import com.selenium.Handlebrowser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Instantiatebrowsertest {
    private WebDriver driver;
    private File logDir;
    private Amazon_home_page_object obj;

    @BeforeClass
    @SuppressWarnings("unchecked")
    public void setUp() throws IOException {
        Map<String, Object> testYamlData = utilities.readYamlData("src/main/java/com/Testdata.yaml");
        Map<String, Object> testData = (Map<String, Object>) testYamlData.get("Amazon_Test_Data");
        String App_URL = (String) testData.get("URL");

        logDir = utilities.createFolder("AmazonSearchTestLogs");
        driver = Handlebrowser.handlewebdriver("Chrome");
        obj = new Amazon_home_page_object(driver, logDir);

        Handlebrowser.launchURL(driver, App_URL);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void searchAndValidate() throws IOException, InvalidFormatException {
        Map<String, Object> locatorYamlData = utilities.readYamlData("src/main/java/com/locators.yaml");
        Map<String, Object> locatorData = (Map<String, Object>) locatorYamlData.get("Amazon_Locators");

        Map<String, Object> testYamlData = utilities.readYamlData("src/main/java/com/Testdata.yaml");
        Map<String, Object> testData = (Map<String, Object>) testYamlData.get("Amazon_Test_Data");

        String Category_dd_Locator = (String) locatorData.get("Category_dd_Xpath");
        String Category_options_Locator = (String) locatorData.get("Category_options_Xpath");
        String Search_tb_Locator = (String) locatorData.get("Search_tb_Xpath");
        String Suggestion_options_Locator = (String) locatorData.get("Suggestion_options_Xpath");
        String Search_button_Locator = (String) locatorData.get("Search_button_Xpath");
        String Search_validation_text_Locator = (String) locatorData.get("Search_validation_text_Xpath");

        String App_Product_Category = (String) testData.get("Product_Category");
        String App_Product_To_Search = (String) testData.get("Product_To_Search");
        String App_Product_Text_To_Select = (String) testData.get("Product_Text_To_Select");
        String App_Product_Search_Validation_text = (String) testData.get("Product_Search_Validation_text");

        obj.SearchProduct(Category_dd_Locator,
                Category_options_Locator, App_Product_Category,
                Search_tb_Locator, App_Product_To_Search,
                Suggestion_options_Locator,
                App_Product_Text_To_Select, Search_button_Locator, "AmazonSearchProductTestCase", 10);

        obj.validateSearch(Search_validation_text_Locator, App_Product_Search_Validation_text,
                "AmazonSearchTestCase1", 10);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
