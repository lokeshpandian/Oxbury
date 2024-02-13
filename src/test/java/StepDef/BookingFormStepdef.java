package StepDef;


import PageObjects.BookingPage;
import Utility.BrowserUtility;
import Utility.PropertiesFileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


public class BookingFormStepdef {

    public static WebDriver driver;
    Properties properties=PropertiesFileReader.getProperty();
    String base_url=properties.getProperty("browser.baseURL");
    String browserName=properties.getProperty("browser.name");

    public By getDaysInWeek() {
        return By.xpath(".//div[@class='rbc-day-bg']");
    }

    @Given("I navigate to site")
    public void i_navigate_to_site() throws InterruptedException {
        driver=BrowserUtility.OpenBrowser(driver,browserName,base_url);
    }

    @When("I fill out the form with the following data")
    public void i_fill_out_the_form_with_the_following_data(List<Map<String, String>> formData) {
        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.clickBookThisRoom();
        for (Map<String, String> row : formData) {
            String firstName = row.get("First Name");
            String lastName = row.get("Last Name");
            String email = row.get("Email");
            String phoneNumber = row.get("PhoneNumber");


            bookingPage.setFirstName(firstName);
            bookingPage.setLastName(lastName);
            bookingPage.setEmail(email);
            bookingPage.setPhone(phoneNumber);

    }}

    @When("I selected the dates")
    public void i_selected_the_dates() {
        List<WebElement> days = driver.findElements(getDaysInWeek());
        WebElement day1 = days.get(0);
        WebElement day2 = days.get(days.size() - 1);

        Actions action = new Actions(driver);

        action.clickAndHold(day2)
                .moveToElement(day2)
                .moveToElement(day1)
                .release(day1) // Changed from dragAndDrop since we're already holding the click from day2 to day1.
                .perform();


    }


    @When("I submit the form")
    public void i_submit_the_form() {
        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.bookForm();
    }

    @Then("I should receive confirmation")
    public void i_should_receive_confirmation() {
        BookingPage bookingPage = new BookingPage(driver);
        assertEquals("Booking confirmed!", bookingPage.getConfirmationMessage());
    }

    @When("I fill out the {string}, {string}, {string}, {string} with only required fields left empty")
    public void i_fill_out_the_with_only_required_fields_left_empty(String firstName, String lastName, String email, String phoneNumber) {
        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.clickBookThisRoom();
        bookingPage.setFirstName(firstName);
        bookingPage.setLastName(lastName);
        bookingPage.setEmail(email);
        bookingPage.setPhone(phoneNumber);

    }

    @Then("I should see error messages indicating missing values")
    public void i_should_see_error_messages_indicating_missing_values() {
        BookingPage bookingPage = new BookingPage(driver);
        assertTrue(bookingPage.getErrorMessage());
    }
    @When("the form should display unavailable dates for the chosen period")
    public void theFormShouldDisplayUnavailableDates() {
        BookingPage bookingPage = new BookingPage(driver);
        assertTrue(bookingPage.getunavailablityMessage());
    }

    @Then("I should see an error message stating that the dates are already taken")
    public void iShouldSeeAnErrorMessage() {
        BookingPage bookingPage = new BookingPage(driver);
        assertTrue(bookingPage.getunavailablityMessage());
    }
}