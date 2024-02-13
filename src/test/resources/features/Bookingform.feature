Feature: Booking form

	@validBooking
	Scenario: Single Booking Validation
		Given I navigate to site
		When I fill out the form with the following data
		| First Name   | Last Name     | Email                        | PhoneNumber               |
		| Lokesh     | pandian             | validEmail@example.com       | 12345678901             |
		And I selected the dates
		And I submit the form
		Then I should receive confirmation


	@Invalidbooking
	Scenario Outline: Verify Required Fields Are Not Empty
		Given I navigate to site
		When I fill out the "<firstName>", "<lastName>", "<email>", "<phoneNumber>" with only required fields left empty
		And I selected the dates
		And I submit the form
		Then I should see error messages indicating missing values

		Examples:
		| firstName   | lastName     | email                        | phoneNumber               |
		|             |              | validEmail@example.com        | 12345678901             |
		|             |              | InvalidEmail                  |                           |
		|             |              | validEmail@example.com        |                           |
		|             |              | validEmail@example.com        | 12345678901             |


	@confirmationAndUnavailabilityCheck
	Scenario: Check Unavailability Message after Successful Submission
		Given I navigate to site
		When I fill out the form with the following data
		| First Name   | Last Name     | Email                        | PhoneNumber               |
		| Lokesh     | pandian           | validEmail@example.com       | 12345678901             |
		And I selected the dates
		And I submit the form
  		Then I should receive confirmation message
  		And the form should display unavailable dates for the chosen period
    	When I selected the dates
    	Then I should see an error message stating that the dates are already taken

