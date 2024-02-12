package StepDef;

import PageObjects.BookingPage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class UnavailablityCheck {
    private WebDriver driver;

    private BookingPage bookingPage;
    @Then("the form should display unavailable dates for the chosen period")
    public void theFormShouldDisplayUnavailableDates() {
        bookingPage = new BookingPage(driver);
        assertTrue(bookingPage.getunavailablityMessage());
    }
    @Then("I should see an error message stating that the dates are already taken")
    public void iShouldSeeAnErrorMessage() {
        bookingPage = new BookingPage(driver);
        assertEquals("The room dates are either invalid or are already booked for one or more of the dates that you have selected.",
                bookingPage.getErrorMessageText());
    }
}
