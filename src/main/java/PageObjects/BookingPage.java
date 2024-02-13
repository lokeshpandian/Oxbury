package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[text()='Book this room']")
    private WebElement bookThisRoom;
    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "phone")
    private WebElement phone;
    @FindBy(name = "email")
    private WebElement email;



    @FindBy(xpath ="//button[normalize-space()='Book']")
    private WebElement bookButton;

    @FindBy(xpath="//div[@class='form-row']//h3")
    private WebElement confirmationMessage;

    @FindBy(id = "errorMessage")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@class='row hotel-room-info']")
    private WebElement unavailablityMessage;
    public BookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    public void fillOutForm(String[][] data) {
//        data.forEach(row -> {
//            firstName.sendKeys(row[0]);
//            lastName.sendKeys(row[1]);
//            email.sendKeys(row[2]);
//            phoneNumber.sendKeys(row[3]);
//        });
//    }
public void setFirstName(String firstName) {
    this.firstName.sendKeys(firstName);
}

    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }


    public void setEmail(String email) {
        this.email.sendKeys(email);
    }



    public void setPhone(String phone) {
        this.phone.sendKeys(phone);
    }
    public void bookForm() {
        this.bookButton.click();
    }

    public void clickBookThisRoom() {

       this.bookThisRoom.click();
    }

    public String getConfirmationMessage() {
        return this.confirmationMessage.getText();
    }

    public Boolean getErrorMessage() {
        return this.errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return this.errorMessage.getText();
    }

    public Boolean getunavailablityMessage() {

        return unavailablityMessage.isDisplayed();
    }
}