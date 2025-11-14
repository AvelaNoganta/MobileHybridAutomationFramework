@mobileWeb @registration
Feature: User Registration Flow

  Scenario: Verify User Registration Flow on Mobile Web Browser
    Given I launch the mobile web browser
    When I navigate to the Ndosi Automation homepage
    Then I should see the page title
    Then I should click on the menu icon
    And I click on the Learning Materials link
    Then I click on Sign Up link

#    Veify the focus and keyboard input on the Sign Up page
    When I verify the focus and keyboard input on the Sign Up page
    Then I Check password visibility toggle
    And I Validate the Create Account button functionality
    Then I Test form validation messages "Ndosi Testimonials API.postman_collection"




