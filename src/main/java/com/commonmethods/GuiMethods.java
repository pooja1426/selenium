package com.commonmethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.io.Files;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.nio.file.Paths;
import io.restassured.response.Response;
import io.restassured.RestAssured;



public class GuiMethods {
    WebDriver driver;
    File logDir;

    public GuiMethods(WebDriver driver, File logDir) {
        this.driver = driver;
        this.logDir = logDir;
    }

    public void validate_click(String locator, int timeout) {
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            exp_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
        } catch (TimeoutException e) {
            System.out.println("Element not found or not clickable: " + e.getMessage());
        }
    }

    public void validate_double_click(String locator, int timeout) {
        Actions actions = new Actions(driver);
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            WebElement element = exp_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            actions.doubleClick(element).build().perform();
        } catch (TimeoutException e) {
            System.out.println("Element not found or not clickable: " + e.getMessage());
        }
    }

    public void validate_right_click(String locator, int timeout) {
        Actions actions = new Actions(driver);
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
           WebElement element = exp_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
           actions.contextClick(element).build().perform();
        } catch (TimeoutException e) {
            System.out.println("Element not found or not clickable: " + e.getMessage());
        }
        }

    public void validate_insert_text(String locator, String value, int timeout) {
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            exp_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).sendKeys(value);
        } catch (TimeoutException e) {
            System.out.println("Element not found or unable to insert text into: " + e.getMessage());
        }
    }

    public void validate_insert_text_uc(String locator, String value, int timeout) {
        Actions actions = new Actions(driver);
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            WebElement element = exp_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            actions.moveToElement(element).keyDown(Keys.SHIFT).sendKeys(value).keyUp(Keys.SHIFT).build().perform();
        } catch (TimeoutException e) {
            System.out.println("Element not found or unable to insert text into: " + e.getMessage());
        }
    }

    public void validate_insert_text_js(String locator, String value, int timeout) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            WebElement wl = exp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            jsExecutor.executeScript("arguments[0].value = '" + value + "';", wl);
        } catch (TimeoutException e) {
            System.out.println("Element not found or unable to insert text into: " + e.getMessage());
        }
    }

     public void drag_and_drop(String sourceLocator, String targetLocator, int timeout) {
        Actions action = new Actions(driver);
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement source = exp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sourceLocator)));
        WebElement target = exp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetLocator)));
        action.dragAndDrop(source, target).build().perform();

    }


    public void validate_click_js(String locator, int timeout) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            WebElement wl = exp_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            jsExecutor.executeScript("arguments[0].click();", wl);
        } catch (TimeoutException e) {
            System.out.println("Element not found or not clickable via JS: " + e.getMessage());
        }
    }

    public void scroll_to_element(String locator, int timeout, String viewport) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            WebElement wl = exp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            jsExecutor.executeScript("arguments[0].scrollIntoView(" + viewport + ");", wl);
        } catch (TimeoutException e) {
            System.out.println("Element not found or not scrollable: " + e.getMessage());
        }
    }

    public String validate_get_text(String locator, int timeout) {
        String value = null;
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            value = exp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).getText();
        } catch (TimeoutException e) {
            System.out.println("Element not found or unable to insert text into: " + e.getMessage());
        }
        return value;
    }

    public void select_option_from_dropdown(String options_locator, String option_text, int timeout) {
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            List<WebElement> options = exp_wait
                    .until(ExpectedConditions
                            .visibilityOfAllElementsLocatedBy(
                                    By.xpath(options_locator)));
            for (WebElement option : options) {
                if (option.getText().equals(option_text)) {
                    option.click();
                    break;
                }
            }
        } catch (TimeoutException e) {
            System.out.println("Option not found in dropdown: " + e.getMessage());
        }

    }

    public void switch_frame(String locator , int timeout){
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement frame = exp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        driver.switchTo().frame(frame);
    }

    public void validate_Broken_Links(String locator, int timeout, String fileName)
            throws IOException, InvalidFormatException {
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        Actions action = new Actions(driver);
        List<WebElement> links = exp_wait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
        for (WebElement link : links) {
            action.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();
        }
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            writeTextToWordFile(fileName, driver.getTitle());
            writeImageToWordFile(fileName);
            Response response = Trigger_Get_API(driver.getCurrentUrl());
            Assert.assertEquals(response.statusCode(), 200, driver.getCurrentUrl() + " application is not reachable");

        }

    }



    public String take_screenshot() {
        String Dir = System.getProperty("user.dir");
        String fileName = driver.getTitle().replaceAll("[^a-zA-Z0-9]", "_"); // Replace invalid characters with
                                                                             // underscores
        File SS = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File ssFile = new File(Dir + "/TestLogs/" + fileName + ".png");
        String ssPath = null;
        try {
            Files.copy(SS, ssFile);
            ssPath = ssFile.getPath();
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
        return ssPath;
    }

    public void createWordFile(String fileName) throws IOException {
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        File docFile = new File(logDir, fileName + ".docx");
        try (FileOutputStream fos = new FileOutputStream(docFile);
                XWPFDocument doc = new XWPFDocument()) {
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();

            run.setText("Log of Test Case :" + fileName);
            run.setBold(true);
            run.setColor("008000");

            doc.write(fos);
        }
    }

    public void writeTextToWordFile(String fileName, String text) throws IOException {
        FileInputStream fin = new FileInputStream(logDir + "\\" + fileName + ".docx");
        XWPFDocument doc = new XWPFDocument(fin);
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();

        run.setText(":::::::::::::::::::::::::::::" + text + ":::::::::::::::::::::::::::::");
        FileOutputStream fos = new FileOutputStream(logDir + "\\" + fileName + ".docx");
        run.setBold(true);
        run.setColor("008000");

        doc.write(fos);
        doc.close();
        fos.close();
    }

    public Response Trigger_Get_API(String url) {
        // Perform a simple GET request and return the response
        return RestAssured.given().relaxedHTTPSValidation().when().get(url);
    }


     @SuppressWarnings("deprecation")
     public void writeImageToWordFile(String fileName) throws IOException, InvalidFormatException {
        FileInputStream fin = new FileInputStream(logDir + "\\" + fileName + ".docx");
        XWPFDocument doc = new XWPFDocument(fin);
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();

        String imgPath = take_screenshot();
        System.out.println("Screenshot saved at: " + imgPath);

        run.addPicture(java.nio.file.Files.newInputStream(Paths.get(imgPath)), Document.PICTURE_TYPE_PNG, imgPath,
                Units.toEMU(1000), Units.toEMU(800));
        FileOutputStream fos = new FileOutputStream(logDir + "\\" + fileName + ".docx");
        run.setBold(true);
        run.setColor("008000");

        doc.write(fos);
        doc.close();
        fos.close();
    }

}



