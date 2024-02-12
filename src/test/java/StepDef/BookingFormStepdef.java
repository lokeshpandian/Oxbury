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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;


public class BookingFormStepdef {

    private WebDriver driver;
    Properties properties=PropertiesFileReader.getProperty();
    String base_url=properties.getProperty("browser.baseURL");
    String browserName=properties.getProperty("browser.name");
    private BookingPage bookingPage;

    @Given("I navigate to site")
    public void i_navigate_to_site() throws InterruptedException {
        BrowserUtility.OpenBrowser(driver,browserName,base_url);
    }

    @When("I fill out the form with the following data")
    public void i_fill_out_the_form_with_the_following_data(io.cucumber.datatable.DataTable dataTable) {

        bookingPage = new BookingPage(driver);
        bookingPage.clickBookThisRoom();
        dataTable.asList(String[].class).forEach(row -> {
            bookingPage.setFirstName(row[0]);
            bookingPage.setLastName(row[1]);
            bookingPage.setEmail(row[2]);
            bookingPage.setPhone(row[3]);
        });
    }

    @When("I selected the dates")
    public void i_selected_the_dates() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement source = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@role='cell'])[18]")));
        WebElement target = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@role='cell'])[20]")));

        js.executeScript("function createEvent(typeOfEvent) {\n" +
                "var event =document.createEvent(\"CustomEvent\");\n" +
                "event.initCustomEvent(typeOfEvent,true, true, null);\n" +
                "event.dataTransfer = {};\n" +
                "return event;\n" +
                "};\n" +
                "function dispatchEvent(element, event) {\n" +
                "if (element.dispatchEvent) {\n" +
                "element.dispatchEvent(event);\n" +
                "}\n" +
                "else if (element.fireEvent) {\n" +
                "element.fireEvent(\"on\" + event.type, event);\n" +
                "}\n" +
                "}\n" +
                "function simulateHTML5DragAndDrop(element, eventName) {\n" +
                "var evt =createEvent(eventName);\n" +
                "dispatchEvent(element, evt);\n" +
                "}\n" +
                "var source = arguments[0];\n" +
                "var target = arguments[1];\n" +
                "simulateHTML5DragAndDrop(source, 'dragstart');\n" +
                "simulateHTML5DragAndDrop(target, 'drop');\n" +
                "simulateHTML5DragAndDrop(document, 'dragend');", source, target);
    }


    @When("I submit the form")
    public void i_submit_the_form() {
        bookingPage = new BookingPage(driver);
        bookingPage.bookForm();
    }

    @Then("I should receive confirmation")
    public void i_should_receive_confirmation() {
        bookingPage = new BookingPage(driver);
        assertEquals("Booking confirmed!", bookingPage.getConfirmationMessage());
    }


}