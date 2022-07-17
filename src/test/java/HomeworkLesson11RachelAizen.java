import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HomeworkLesson11RachelAizen {

    private static WebDriver driver;

    @BeforeClass
    public void beforeAll()
    {driver = DriverSingleton.getDriverInstance();
    driver.get("https://dgotlieb.github.io/Navigation/Navigation.html");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));}

    //1
    @Test
    public void iFrame()
    {WebElement iFrameElement = driver.findElement(By.cssSelector("iframe[src='newFrame.html']"));
    driver.switchTo().frame(iFrameElement);
    System.out.println("Text is: " + driver.findElement(By.id("iframe_container")).getText());
    driver.switchTo().defaultContent();
    Assert.assertEquals("Navigation", driver.findElement(By.id("title")).getText());}

    //2  - In Report Class

    //3
    @Test
    public void XML()
    {driver.navigate().to(getData("URL"));}

    private static getData(String keyName)
    {File config = new File("C:\\Intel\\SELENIUM\\HomeworkLesson11\\src\\main\\resources\\data.xml");
    DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilderFactory DBuilder = null;
    try {DBuilder = DBFactory.newDocumentBuilder();}
    catch (ParserConfigurationException e){e.printStackTrace();}
        Document docu = null;
    try {assert DBuilder != null;
    docu = DBuilder.parse(config);}
    catch (IOException e){e.printStackTrace();}
    catch (SAXException e){e.printStackTrace();}
    if (docu != null){docu.getDocumentElement().normalize();}
    assert docu != null;
    return docu.getElementsByTagName(keyName).item(0).getTextContent();}

    //4
    @Test
    public void alertTest()
    {driver.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
    driver.findElement(By.id("MyAlert")).click();
    Alert alert = driver.switchTo().alert();
    System.out.println(alert.getText());
    alert.accept();}

    @Test
    public void prompting()
    {driver.findElement(By.id("MyPrompt")).click();
    Alert prompts = driver.switchTo().alert();
    prompts.sendKeys("Rachel");
    prompts.accept();
    String output = "Rachel";
    asserEquals(output, driver.findElement(By.id("output")).getText());}

    @Test
    public void confirming()
    {driver.findElement(By.id("MyConfirm")).click();
    Alert confirmByBox = driver.switchTo().alert();
    confirmByBox.accept();
    String output2 = "Confirmed";
    asserEquals(output2, driver.findElement(By.id("output")).getText());}

    @AfterClass
    public void afterAll()
    {driver.quit();}
}
