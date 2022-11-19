package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import util.CommonFunctions;
import util.MyScreenRecorder;
import util.PropertyReaders;
import webdriversupport.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GeneralStepDefs {

    private static final Logger logger= Logger.getLogger(GeneralStepDefs.class);
    private Scenario scenario;
    private static Properties config= new Properties();
    private static InputStream input= null;
    private static String configProperty= System.getProperty("user.dir")+ "/src/test/resources/properties/config.properties";
    private static PropertyReaders propertyReaders= new PropertyReaders();

    public String getURL() throws IOException {
        try {
           input= new FileInputStream(configProperty);
           config.load(input);
        }catch (IOException e){}
        if(System.getProperty("environment")!= null){
            propertyReaders.writeProperty("ENV",System.getProperty("environment"));
            return String.format(config.getProperty("URL"),config.getProperty("ENVIRONMENT_"+System.getProperty("environment")));
        } else return String.format(config.getProperty("URL"),config.getProperty("ENVIRONMENT_"+System.getProperty("ENV")));
    }

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        this.scenario=scenario;
        logger.info("Scenario started..");
        logger.info("Executing scenario with tags:"+scenario.getName()+" "+scenario.getSourceTagNames());
        MyScreenRecorder.startRecording(scenario.getName().substring(0,10));

    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        try{
            this.scenario=scenario;
            new CommonFunctions().takeScreenshot(scenario, DriverManager.getDriver());
            MyScreenRecorder.stopRecording();
        }
        finally {
            DriverManager.closeDriver();
        }
    }

}
