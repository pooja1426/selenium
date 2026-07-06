package com.selenium;

import org.openqa.selenium.WebDriver;
import com.commonmethods.GuiMethods;
import com.commonmethods.utilities;
import java.io.File;

public class Makemytriplaunch {
  public static void main(String[] args) {

    WebDriver driver;
    File logDir = utilities.createFolder("goibibologs");
    driver = Handlebrowser.handlewebdriver("Chrome");
    GuiMethods uimethods = new GuiMethods(driver, logDir);
    Handlebrowser.launchURL(driver, "https://www.goibibo.com/");
    driver.manage().window().maximize();
    
    uimethods.validate_click("//span[@class=\"logSprite icClose\"]", 10);


  }
}
