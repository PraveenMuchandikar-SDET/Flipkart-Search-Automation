package demo.wrappers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import demo.Utils.WaitUtils;

public class Wrappers {

    public static boolean navigateToUrl(WebDriver driver, String url) {
        try {
            driver.navigate().to(url);
            return true;
        } catch (Exception e) {
            System.err.println("Error while navigating to URL: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickOnElement(WebDriver driver, WebElement element) {
        try {
            if (WaitUtils.waitForClickable(driver, element)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error while clicking on element: " + e.getMessage());
        }
        return false;
    }

    public static boolean sendKeys(WebDriver driver, WebElement element, String keysToSend) {
        try {
            if (WaitUtils.waitForVisibility(driver, element)) {
                element.clear();
                element.sendKeys(keysToSend);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error while sending keys: " + e.getMessage());
        }
        return false;
    }
}
