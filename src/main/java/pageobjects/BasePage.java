package pageobjects;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriversupport.DriverManager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePage {

    public WebDriver driver;
    public static final Logger logger = Logger.getLogger(BasePage.class);
    private static final long TIMEOUT = 10;

    public BasePage(){
        this.driver= DriverManager.getDriver();
        DOMConfigurator.configure("log4j.xml");
    }

    public WebElement getElement(By locator){
        WebElement element = null;
        int attempts = 0;
        while(attempts<3)
        {
            try {
                logger.info("gettingLocator::"+locator);
                element=driver.findElement(locator);
                break;
            }catch (StaleElementReferenceException e){
                Assert.fail("Unable to get element:"+ e.getMessage());
            }catch (NoSuchElementException e){
                Assert.fail("Unable to locate element:"+ e.getMessage());
            }
            attempts++;
        }
        return element;
    }

    public void waitForElementVisibility(By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception e){
            logger.error("Element was not visible:"+ e.getMessage());
            Assert.fail("Element was not visible"+ e.getMessage());
        }
    }

    public void waitForVisibilityOfElement(WebElement element){
        int attempts=0;
        while (attempts<3){
            try{
                new WebDriverWait(driver,TIMEOUT).until(ExpectedConditions.visibilityOf(element));
                break;
            }catch (Exception e){
                logger.error("Element was not visible:"+ e.getMessage());
                Assert.fail("Element was not visible"+ e.getMessage());
            }
            attempts++;
        }
    }

    public void waitForPageToLoad(){
        new WebDriverWait(driver,TIMEOUT).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver){
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public void clickElement(By locator){
        waitForElementVisibility(locator);
        waitForVisibilityOfElement(getElement(locator));
        getElement(locator).click();
        logger.info("element clicked successfully:"+locator);
    }

    public void enterValues(By locator,String values){
        driver.findElement(locator).sendKeys(values);
    }

    public List<WebElement> getAllElements(By locator){
        return driver.findElements(locator);
    }

    public void ddSelectByVisibleText(WebElement elm,String txt){
        Select select=new Select(elm);
        select.selectByVisibleText(txt);
        logger.info("Option selected for the DropDown is:"+txt);
    }

    public void scrollIntoView(WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }


}
