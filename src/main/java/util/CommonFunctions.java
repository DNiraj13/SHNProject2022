package util;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class CommonFunctions {

    public void takeScreenshot(Scenario scenario, WebDriver driver)
    {
        if(scenario.isFailed()){
            try{
                byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot,"image/jpeg","Failed step Image");
            }catch (WebDriverException wde){
                System.err.println(wde.getMessage());
            }catch (ClassCastException cce){
                cce.printStackTrace();
            }
        }
    }
}
