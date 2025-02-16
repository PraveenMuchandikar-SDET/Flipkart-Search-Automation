package demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import demo.Utils.WaitUtils;
import demo.wrappers.Wrappers;

public class HomePage {

    private WebDriver driver;
    private static final String URL = "https://www.flipkart.com/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    @FindBy(name = "q")
    private WebElement searchInputTextbox;

    /**
     * Navigate to the Home Page of Flipkart.
     */
    public void navigateToHomePage() {
        Wrappers.navigateToUrl(driver, URL);
    }

    /**
     * Search for the product.
     */
    public boolean searchForProduct(String product) {
        try {
            Wrappers.sendKeys(driver, searchInputTextbox, product);
            searchInputTextbox.submit();
            WaitUtils.sleep(2000);
            return true;
        } catch (Exception e) {
            System.err.println("Error while searching for product: " + e.getMessage());
            return false;
        }
    }
}
