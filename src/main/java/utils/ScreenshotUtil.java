package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File dir = new File("screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            FileHandler.copy(src, new File(dir + "/" + testName + "_" + timestamp + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
