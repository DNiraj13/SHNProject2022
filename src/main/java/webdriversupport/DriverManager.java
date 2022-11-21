package webdriversupport;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.PropertyReaders;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    static String browser= null;
   // private static DriverManager instance = new DriverManager();

    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(){
        @Override
        protected WebDriver initialValue(){
            if(System.getProperty("browser") != null){
                browser=System.getProperty("browser");
            } else{
                try {
                    browser= new PropertyReaders().readProperty("BROWSER");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            switch (browser){
                case "firefox":
                    return getFireFoxDriver();
                case "chrome":
                    return getChromeDriver();
                case "docker":
                    return getChromeOnDocker();
                default:
                    return getChromeDriver();
            }

        }
    };


   /* private DriverManager() {  }*/

    public static WebDriver getDriver()
    {
        return driver.get();
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--headless");
        WebDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        chromeDriver.manage().window().setSize(new Dimension(1208, 1024));
        //chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    private static WebDriver getFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }

    private static WebDriver getChromeOnDocker(){
        RemoteWebDriver driver= null;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        try{
            driver= new RemoteWebDriver(new URL(getHubUrl()), capabilities);
            driver.manage().window().maximize();
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return driver;
    }

    private static String getHubUrl() {

        String runFromEnv=null;
        try {
            runFromEnv = new PropertyReaders().readProperty("RUN_ENVIRONMENT");
        }catch (IOException e){
            e.printStackTrace();
        }
        if(runFromEnv.equalsIgnoreCase("docker")){
            return "http://hub:4444/wd/hub";                  // for docker container
        } else return "http://0.0.0.0:4444/wd/hub";           // for local machine Selenium hub
    }

    public static void closeDriver(){
        driver.get().close();
        driver.remove();
    }
}
