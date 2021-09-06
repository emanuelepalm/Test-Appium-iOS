package base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import appium.AppiumServer;
import com.sun.org.apache.xpath.internal.operations.And;
import emu.AndroidEmu;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentReports;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import static parameter.GlobalParameters.APK_PATH;
import static parameter.GlobalParameters.USERDIR;

public class BaseClass {

        public static AppiumDriver<MobileElement> driver;
        public static AppiumServer server;
        protected static ExtentReports extentReports;

        public String emuName = "iPhone 12 pro max";

        @BeforeSuite
        @Parameters({"suitename"})
        public void init(String suiteName){

            extentReports = new ExtentReports(USERDIR + File.separator + "report" + File.separator + suiteName + ".html", true);
            extentReports.loadConfig(new File(USERDIR + File.separator + "report_config.xml"));

        }

        @BeforeTest
        @Parameters({"udid", "platformVersion","url1","url2","appname"})
        public void setup(String udid,String platformVersion,String url1, String url2, String appName) throws InterruptedException, MalformedURLException
        {
            server = new AppiumServer();
            Thread.sleep(10000);


            DesiredCapabilities cap=new DesiredCapabilities();
            cap.setCapability("deviceName", emuName);
            cap.setCapability("udid", udid);
            cap.setCapability("app", APK_PATH + appName + ".app");
            cap.setCapability("platformName", "ios");
            cap.setCapability("platformVersion", platformVersion);
            cap.setCapability("automationName","XCUITest");
            driver=new AppiumDriver<MobileElement>(new URL(url1 + server.getPort() + url2), cap);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }

        @AfterTest
        public void tearDown() throws InterruptedException {
            Thread.sleep(5000);
            server.stop();

        }

        @AfterSuite
        public void end() {
            extentReports.close();
            System.out.println("APPIUM STOPPING");
        }
}

