package demo.Utils;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    public static boolean waitForVisibility(WebDriver driver, WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            System.err.println("Element is not visible: " + e.getMessage());
            return false;
        }
    }

    public static boolean waitForClickable(WebDriver driver, WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            System.err.println("Element is not clickable: " + e.getMessage());
            return false;
        }
    }

    public static boolean waitForVisibilityOfAllElements(WebDriver driver, List<WebElement> elements) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(elements));
            return true;
        } catch (Exception e) {
            System.err.println("Elements are not visible: " + e.getMessage());
            return false;
        }
    }

    public static void sleep(long milliseconds) {
        try {
           Thread.sleep(milliseconds);
        } catch (Exception e) {
           System.out.println("Thread sleep interrupted: " + e.getMessage());
        }
    }
}
