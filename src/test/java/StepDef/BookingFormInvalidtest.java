package StepDef;

import PageObjects.BookingPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class BookingFormInvalidtest {
    private WebDriver driver;

    private BookingPage bookingPage;

    @When("I fill out the {string}, {string}, {string}, {string} with only required fields left empty")
    public void i_fill_out_the_with_only_required_fields_left_empty(String firstName, String lastName, String email, String phoneNumber) {
        bookingPage = new BookingPage(driver);
        bookingPage.clickBookThisRoom();
        bookingPage.setFirstName(firstName);
        bookingPage.setLastName(lastName);
        bookingPage.setEmail(email);
        bookingPage.setPhone(phoneNumber);

    }

    @Then("I should see error messages indicating missing values")
    public void i_should_see_error_messages_indicating_missing_values() {
        bookingPage = new BookingPage(driver);
        assertTrue(bookingPage.getErrorMessage());
    }
}