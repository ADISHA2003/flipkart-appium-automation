package tests;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverManager;

import java.net.MalformedURLException;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class BaseTest {

    protected DriverManager driverManager;
    protected AppiumDriver driver;
    protected static String platformName = "an";
    protected AppiumDriverLocalService service;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
        platformName = System.getProperty("platform", platformName);
        driverManager = new DriverManager();
        driver = driverManager.initDriver(platformName);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driverManager != null) {
            driverManager.quitDriver();
        }

        // Stop Appium Server

        if(service != null){

            service.stop();

            System.out.println("Appium Server Stopped");
        }
    }
}
