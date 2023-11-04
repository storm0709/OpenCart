package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;


public class BaseTest {
    public static WebDriver driver;
    public Logger logger; //for logging

    public ResourceBundle rb;

    @BeforeClass(groups={"Regression", "Sanity", "Master"})
    @Parameters("browser")
    public void setUp(String br){
        rb=ResourceBundle.getBundle("config"); //load config.properties file
        logger= LogManager.getLogger(this.getClass()); //logging

        //ChromeOptions options=new ChromeOptions();
        //options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        if(br.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(br.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(rb.getString("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups={"Regression", "Sanity", "Master"})
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        String generatedStr= RandomStringUtils.randomAlphabetic(5);
        return generatedStr;
    }

    public String randomNum(){
        String generatedStr2=RandomStringUtils.randomNumeric(10);
        return generatedStr2;
    }

    public String randomAlphaNum(){
        String generatedStr3=RandomStringUtils.randomAlphanumeric(7);
        return generatedStr3;
    }

    public String captureScreen(String tname) throws IOException {
        SimpleDateFormat df=new SimpleDateFormat();
        String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;
    }
}
