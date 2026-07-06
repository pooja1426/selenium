package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.commonmethods.APIMethods;

import io.restassured.response.Response;


public class Handlebrowser extends APIMethods {
    public static WebDriver handlewebdriver(String browser) {
        WebDriver driver;
        switch (browser) {
               case "Chrome":
                ChromeOptions copt = new ChromeOptions();
                copt.addArguments("--start-maximized");
                copt.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new ChromeDriver(copt);
                break;
            case "chromeincognito":
                ChromeOptions cig = new ChromeOptions();
                cig.addArguments("--start-maximized");
                driver = new ChromeDriver(cig);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }
    public static void launchURL(WebDriver driver, String URL) {
        driver.get(URL);
        Response response = Trigger_Get_API(driver.getCurrentUrl());
        Assert.assertEquals(response.statusCode(), 200, URL +" application is not reachable");
    }

}
