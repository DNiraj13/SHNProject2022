Feature: Validate the person Eligibility Criteria

  @Smoke
  Scenario Outline: Verify Eligibility Criteria for the given person
    Given user is in Eligibility Checker homePage
    When user select its "<Country>","<GPOption>" and applicable "<DentalCountry>"
    And user enters its "<DateOfBirth>"
    And user enters its "<PartnerStatus>","<PartnerClaimBenefits>","<PregnancyStatus>"
    Then user enter its details regarding "<MilitaryServingInjuryStatus>","<DiabetesStatus>","<GlaucomaStatus>","<CareHomeStatus>","<SavingsRange>"

    Examples:
      | Country | GPOption | DentalCountry | DateOfBirth | PartnerStatus | PartnerClaimBenefits | PregnancyStatus | MilitaryServingInjuryStatus | DiabetesStatus | GlaucomaStatus | CareHomeStatus | SavingsRange |
      | Wales   | Yes      | Wales         | 10-02-1998  | Yes           | No                   | Yes             | Yes                         | Yes            | Yes            | Yes            | Yes          |
      | Wales   | No       | Scotland      | 10-02-1970  | No            | No                   | No              | No                          | No             | No             | No             | No           |


  @Smoke
  Scenario Outline: Verify Eligibility Criteria for the given person with partner claiming benefits
    Given user is in Eligibility Checker homePage
    When user select its "<Country>","<GPOption>" and applicable "<DentalCountry>"
    And user enters its "<DateOfBirth>"
    And user enters its "<PartnerStatus>","<PartnerClaimBenefits>" and "<UniversalCreditPayment>" details
    Then user enter its details regarding "<MilitaryServingInjuryStatus>","<DiabetesStatus>","<GlaucomaStatus>","<CareHomeStatus>","<SavingsRange>"

    Examples:
      | Country | GPOption | DentalCountry | DateOfBirth | PartnerStatus | PartnerClaimBenefits | UniversalCreditPayment | MilitaryServingInjuryStatus | DiabetesStatus | GlaucomaStatus | CareHomeStatus | SavingsRange |
      | Wales   | Yes      | Wales         | 10-02-1998  | Yes           | Yes                  | yes-universal          | Yes                         | No             | No             | Yes            | No           |
      | Wales   | Yes      | Wales         | 10-02-1963  | No            | Yes                  | not-yet                | No                          | Yes            | Yes            | No             | Yes          |