package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;


public class EligibilityChecker extends BasePage {

    public static final Logger logger = Logger.getLogger(EligibilityChecker.class);

    By CookiesAccept= By.id("nhsuk-cookie-banner__link_accept_analytics");
    By CheckerHomePageText= By.xpath("//h1[text()='Check what help you could get to pay for NHS costs']");
    By StartNow = By.xpath("//input[@value='Start now']");
    String SelectCountry= "//label[text()='{country}']";
    By Next = By.xpath("//input[@value='Next']");
    String YesNoOption= "//label[text()='{OptionToSelect}']";
    By Enter_DOBDate= By.xpath("//input[@id='dob-day']");
    By Enter_DOBMonth= By.xpath("//input[@id='dob-month']");
    By Enter_DOBYear= By.xpath("//input[@id='dob-year']");
    //By Enter_DOBDate= By.xpath("//div[starts-with(@class,'titleWrapper')]/following::div/following::div/div/ul/li[3]");


    public void navigateToSite( String url) {
        driver.get(url);
        waitForPageToLoad();
        logger.info("User is in home page");
        waitForElementVisibility(CookiesAccept);
        clickElement(CookiesAccept);
        logger.info("User accepts all cookies");
    }

    public void startEligibilityChecker(String country, String gpOption, String dentalCountry) throws InterruptedException {
        waitForElementVisibility(StartNow);
        clickElement(StartNow);
        logger.info("User clicks on Start now button");
        clickElement(By.xpath(SelectCountry.replace("{country}",country)));
        clickElement(Next);
        logger.info("User selects the country and clicks on next");
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",gpOption)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the GPoption and clicks on next");
        clickElement(By.xpath(SelectCountry.replace("{country}",dentalCountry)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the DentalCountry and clicks on next");

    }

    public void enterDOB(String dob){
        String DOB_date=dob.split("-")[0];
        String DOB_month=dob.split("-")[1];
        String DOB_year=dob.split("-")[2];
        enterValues(Enter_DOBDate,DOB_date);
        enterValues(Enter_DOBMonth,DOB_month);
        enterValues(Enter_DOBYear,DOB_year);
        clickElement(Next);
        logger.info("User enters the" +dob+ "and clicks on next");
    }

    public void enterPartnerDetails(String partnerStatus,String partnerClaimBenefit,String pregnancyStatus) throws InterruptedException {
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",partnerStatus)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the PartnerStatus and clicks on next");
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",partnerClaimBenefit)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the PartnerClaimBenefit and clicks on next");
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",pregnancyStatus)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the PregnancyStatus and clicks on next");

    }

    public void enterPartnerDetailWithClaimBenefits(String partnerStatus, String partnerClaimBenefits, String universalCreditPayment) throws InterruptedException {
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",partnerStatus)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the PartnerStatus and clicks on next");
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",partnerClaimBenefits)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the PartnerClaimBenefits and clicks on next");
        clickElement(By.xpath("//label[@for='"+universalCreditPayment+"']"));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the UniversalCreditPayment and clicks on next");
        if(universalCreditPayment.contains("yes")){
            clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}","Yes")));
            scrollIntoView(getElement(Next));
            clickElement(Next);
            clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}","No")));
            scrollIntoView(getElement(Next));
            clickElement(Next);
            clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}","No")));
            scrollIntoView(getElement(Next));
            clickElement(Next);
        }
        else if(universalCreditPayment.contains("not")){
            System.out.println("Second");
            scrollIntoView(getElement(Next));
            clickElement(Next);
            clickElement(By.xpath("//label[@class='noneOption']"));
            scrollIntoView(getElement(Next));
            clickElement(Next);
            clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}","Yes")));
            scrollIntoView(getElement(Next));
            clickElement(Next);

        }
    }

    public void userEnterHealthHistory(String militaryInjuryStatus, String diabetesStatus, String glaucomaStatus, String careHomeStatus, String savingsRange) throws InterruptedException {
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",militaryInjuryStatus)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the MilitaryInjuryStatus and clicks on next");
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",diabetesStatus)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the DiabetesStatus and clicks on next");
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",glaucomaStatus)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the GlaucomaStatus and clicks on next");
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",careHomeStatus)));
        scrollIntoView(getElement(Next));
        clickElement(Next);
        logger.info("User selects the CareHomeStatus and clicks on next");
        if(careHomeStatus.equalsIgnoreCase("Yes")){
            clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}","Yes")));
            scrollIntoView(getElement(Next));
            clickElement(Next);
        }
        if(careHomeStatus.equalsIgnoreCase("No")){
        clickElement(By.xpath(YesNoOption.replace("{OptionToSelect}",savingsRange)));
        scrollIntoView(getElement(Next));
        clickElement(Next);}
    }




}
