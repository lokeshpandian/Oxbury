package Utility;

import org.openqa.selenium.*;

public class TestUtilities {

    public static void scrollToElement(WebElement element, WebDriver driver) {
        int yPos = element.getLocation().getY();
        int windowSize = driver.manage().window().getSize().getHeight();
        int scrollPosition = yPos - (windowSize / 2);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0]);", scrollPosition);
    }
    public static boolean exists(WebDriver driver, By elementBy) {
        try {
            return driver.findElement(elementBy).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
