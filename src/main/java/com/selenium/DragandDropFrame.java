package com.selenium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.commonmethods.GuiMethods;
import com.commonmethods.utilities;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class DragandDropFrame {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        WebDriver driver;
        File logDir = utilities.createFolder("DragAndDropWithFrame");
        driver = Handlebrowser.handlewebdriver("Chrome");
        GuiMethods uimethods = new GuiMethods(driver, logDir);
        uimethods.createWordFile("DragandDropTestWithFrame");

        Handlebrowser.launchURL(driver, "https://jqueryui.com/droppable/");
        uimethods.writeTextToWordFile("DragandDropTestWithFrame",
                "Pre Drag Drop Operation");
        try {
            uimethods.writeImageToWordFile("DragandDropTestWithFrame");
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        uimethods.switch_frame("//iframe[@class=\"demo-frame\"]", 5);
        uimethods.drag_and_drop("//div[@id=\"draggable\"]", "//div[@id=\"droppable\"]", 5);

        uimethods.writeTextToWordFile("DragandDropTestWithFrame",
                "Post Drag Drop Operation");
        try {
            uimethods.writeImageToWordFile("DragandDropTestWithFrame");
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver.switchTo().parentFrame();

        driver.quit();

    }

}
