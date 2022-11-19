package stepdefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.PageObjectManager;

import java.io.IOException;


public class ApplicationStepDefs extends PageObjectManager {

    private final GeneralStepDefs generalStepDefs=new GeneralStepDefs();

    @Given("user is in Eligibility Checker homePage")
    public void user_is_in_eligibility_checker_home_page() throws Throwable {
        eligibilityChecker.navigateToSite(generalStepDefs.getURL());
    }

    @When("user select its {string},{string} and applicable {string}")
    public void user_select_its_and_applicable(String country, String gpOption, String dentalCountry) throws InterruptedException {
        eligibilityChecker.startEligibilityChecker(country,gpOption,dentalCountry);
    }

    @When("user select its {string}")
    public void user_select_its(String countryName) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("user select GP practise location {string}")
    public void user_select_gp_practise_location(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("select the {string} for its dental practise")
    public void select_the_for_its_dental_practise(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("user enters its {string}")
    public void user_enters_its(String dob) {
        eligibilityChecker.enterDOB(dob);
    }
    @Then("user enters its {string},{string},{string}")
    public void user_enters_its(String partnerStatus, String partnerClaimBenefits, String pregnancyStatus) throws InterruptedException {
        eligibilityChecker.enterPartnerDetails(partnerStatus,partnerClaimBenefits,pregnancyStatus);
    }
    @Then("user enter its details regarding {string},{string},{string},{string},{string}")
    public void user_enter_its_details_regarding(String militaryInjuryStatus, String diabetesStatus, String glaucomaStatus, String careHomeStatus, String savingsRange) throws InterruptedException {
        eligibilityChecker.userEnterHealthHistory(militaryInjuryStatus,diabetesStatus,glaucomaStatus,careHomeStatus,savingsRange);
    }

    @And("user enters its {string},{string} and {string} details")
    public void userEntersItsAndDetails(String partnerStatus, String partnerClaimBenefits, String universalCreditPayment) throws InterruptedException {
        eligibilityChecker.enterPartnerDetailWithClaimBenefits(partnerStatus,partnerClaimBenefits,universalCreditPayment);
    }


}
