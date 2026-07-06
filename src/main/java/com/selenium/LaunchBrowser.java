package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowser {
    public static void main(String[] args) {
       // System.setProperty("webdriver.chrome.driver", 
       // "C:\\Users\\Hello\\GUIautomation\\drivers\\chromedriver-win64\\chromedriver-win64.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }
}
