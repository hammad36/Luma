package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
            File dir = new File(screenshotDir);


            String fullPath = screenshotDir + testName + "_" + timestamp + ".png";
            FileHandler.copy(src, new File(fullPath));
            return fullPath;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
