import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Duration;

public class Report {

    private static ChromeDriver driver;

    private static ExtentReports extent;

    private static ExtentTest test;

    @BeforeClass
    public static void beforeAll() throws FileNotFoundException
    {String way = System.getProperty("user.dir");
    ExtentSparkReporter Reporter = new ExtentSparkReporter(way + "\\extent.html");
    extent = new ExtentReports();
    extent.attachReporter(Reporter);
    test = extent.createTest("First Test", "Trying");
    extent.setSystemInfo("Meals", "Costumers");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
    test.log(Status.PASS, "Driver is done!");

    PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
    System.setOut(out);}

    @Test
    public void textField() throws IOException
    {driver.get("https://translate.google.com/");
    String time = String.valueOf(System.currentTimeMillis());
    test.info("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(time)).build());
    driver.findElement(By.id("source")).click();
    test.log(Status.PASS, "Click Translate Box");}

    @AfterClass
    public void endItAll()
    {test.log(Status.INFO, "Done!");
    driver.quit();
    extent.flush();}

    private static String takeScreenShot(String ImagePath)
    {TakesScreenshot taking = (TakesScreenshot) driver;
    File screenShotFile = TakesScreenshot.getScreenshotAs(OutputType.FILE);
    File destinationFile = new File(ImagePath + ".png");
    try {FileUtils.copyFile(screenShotFile, destinationFile);}
    catch (IOException e){System.out.println(e.getMessage());}
    return ImagePath + ".png";}
}
