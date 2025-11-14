@mobileWeb @login
Feature:Login Process Testing

  Scenario Outline: Verify User Login Flow on Mobile Web Browser
    Given I launch the mobile web browser login
    When I navigate to the Ndosi Automation homepage login
    Then I should click on the menu icon login
    And I click on the Learning Materials link login
    Then I test the email and password field with input type "<inputType>" and email "<email>" and "<password>" and validate Alert popup

    Examples:
      | inputType                   | email            | password      |
      | empty                       |                  |               |
      | invalid password            | avnog@gmail.com  | wrongPass     |
      | invalid username            | invalid@mail.com | Testing123456 |
      | valid username and password | avnog@gmail.com  | Testing123456 |

