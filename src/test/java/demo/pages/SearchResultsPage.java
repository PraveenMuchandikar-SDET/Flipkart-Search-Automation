package demo.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import demo.Utils.WaitUtils;
import demo.wrappers.Wrappers;

public class SearchResultsPage {
    private WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'zg-M3Z')]")
    private List<WebElement> sortByList;

    /**
     * Selects a "Sort By" option based on the given text.
     */
    public void sortBySelection(String optionText) {
        for (WebElement option : sortByList) {
            if (WaitUtils.waitForVisibility(driver, option) && option.getText().equalsIgnoreCase(optionText)) {
                Wrappers.clickOnElement(driver, option);
                WaitUtils.sleep(1000);
                break;
            }
        }
    }

    /**
     * Selects the customer rating checkbox corresponding to the provided rating text.
     */
    public void selectCustomerRatingChkBox(String ratingText) {
        String xpath = String.format("//div[@class='_6i1qKy' and contains(text(), '%s')]", ratingText);
        try {
            WebElement ratingCheckbox = driver.findElement(By.xpath(xpath));
            Wrappers.clickOnElement(driver, ratingCheckbox);
            WaitUtils.sleep(2000);
        } catch (Exception e) {
            System.err.println("Customer rating checkbox not found: " + e.getMessage());
        }
    }

    /**
     * Counts the number of items with a rating of 4.0 or less on the search results page.
     */
    public int countItemsWithRatingLessThanOrEqualTo4() {
        int count = 0;
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class, 'tUxRFH')]"));
        for (WebElement item : searchResults) {
            try {
                WebElement ratingElement = item.findElement(By.xpath(".//span[contains(@id, 'productRating')]/div"));
                double rating = Double.parseDouble(ratingElement.getText());
                if (rating <= 4.0) count++;
            } catch (Exception e) {
                System.err.println("Skipping item due to error: " + e.getMessage());
            }
        }
        return count;
    }

    /**
     * Prints the title and discount for items with a discount of 17% or more.
     */
    public void printTitleAndDiscountForEligibleItems() {
        int count = 0;
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class, 'tUxRFH')]"));
        for (WebElement item : searchResults) {
            try {
                WebElement titleElement = item.findElement(By.xpath(".//div[@class='KzDlHZ']"));
                WebElement discountElement = item.findElement(By.xpath(".//div[@class='UkUFwK']/span"));
                int discountNumber = Integer.parseInt(discountElement.getText().replaceAll("[^0-9]", ""));
                if (discountNumber >= 17) {
                    System.out.println("Title: " + titleElement.getText().trim());
                    System.out.println("Discount: " + discountElement.getText().trim());
                    count++;
                }
            } catch (Exception e) {
                System.err.println("Error processing item: " + e.getMessage());
            }
        }
        if (count <= 0) {
            System.out.println("No product found for discount above 17%");
        }
    }

    /**
     * Prints the top 5 items based on the number of reviews.
     */
    public void printTop5ItemsByReviews() {
        List<HashMap<String, Object>> itemList = new ArrayList<>();
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='slAVV4']"));
        for (WebElement item : searchResults) {
            try {
                WaitUtils.waitForVisibility(driver, item);
                WebElement titleElement = item.findElement(By.xpath(".//a[@class='wjcEIp']"));
                WebElement imageElement = item.findElement(By.xpath(".//a[@class='VJA3rP']//img"));
                List<WebElement> reviewsElement = item.findElements(By.xpath(".//span[@class='Wphh3N']"));

                HashMap<String, Object> details = new HashMap<>();
                details.put("title", titleElement.getText().trim());
                details.put("imageUrl", imageElement.getAttribute("src"));
                
                if (!reviewsElement.isEmpty()) {
                    int reviewCount = Integer.parseInt(reviewsElement.get(0).getText().replaceAll("[^0-9]", ""));
                    details.put("reviews", reviewCount);
                } else {
                    continue;
                }
                itemList.add(details);
            } catch (Exception e) {
                System.err.println("Skipping item due to error: " + e.getMessage());
            }
        }

        // Sort items by review count in descending order
        itemList.sort((item1, item2) -> Integer.compare((int) item2.get("reviews"), (int) item1.get("reviews")));

        // Print details of the top 5 items
        System.out.println("Top 5 items by reviews:");
        for (int i = 0; i < Math.min(5, itemList.size()); i++) {
            Map<String, Object> item = itemList.get(i);
            System.out.println("Title: " + item.get("title"));
            System.out.println("Review Count: " + item.get("reviews"));
            System.out.println("Image URL: " + item.get("imageUrl"));
            System.out.println("------------------------------");
        }
    }
}
