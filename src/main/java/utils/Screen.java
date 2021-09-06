package utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import static parameter.GlobalParameters.USERDIR;


public class Screen {

    public static String getBase64MobileScreenshot(AppiumDriver driver) {
        try {
            SimpleDateFormat oSDF = new SimpleDateFormat("yyyyMMddHHmmss");
            String sDate = oSDF.format(new Date());
            String encodedBase64 = null;
            FileInputStream fileInputStream = null;
            String destination = "";
            File source = null;

            source = driver.getScreenshotAs(OutputType.FILE);
            destination = USERDIR + File.separator + "screen" + File.separator + "screenshot.png";

            File finalDestination = new File(destination);
            FileUtils.copyFile(Objects.requireNonNull(source), finalDestination);

            try {
                fileInputStream = new FileInputStream(finalDestination);
                byte[] bytes = new byte[(int) finalDestination.length()];
                int fileSize = fileInputStream.read(bytes);
                encodedBase64 = new String(Base64.encodeBase64(bytes));

            } catch (FileNotFoundException ex) {
            } finally {
                if(fileInputStream != null) fileInputStream.close();
            }
            return "data:image/png;base64," + encodedBase64;
        } catch (Exception ex) {
        }
        return null;
    }

}
