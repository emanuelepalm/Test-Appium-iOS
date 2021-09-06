
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import base.BaseClass;
import utils.Screen;

import java.io.File;

import static parameter.GlobalParameters.USERDIR;


public class Test_001 extends BaseClass {

    static private ExtentTest extentTest;

    @Test
    public void setup() throws InterruptedException {
        try {

            extentTest = extentReports.startTest("TEST AUTOMATION");

            Thread.sleep(5000);
            extentTest.log(LogStatus.PASS,"PASS",extentTest.addBase64ScreenShot(Screen.getBase64MobileScreenshot(driver)));
            System.out.println("SUCCESS!!!");

            extentReports.endTest(extentTest);
        } catch (Exception e){
            System.out.println("FAILED: " + e.getMessage());
        }
    }
}