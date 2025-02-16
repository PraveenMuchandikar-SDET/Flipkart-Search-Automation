package demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import static demo.Utils.ExtentTestManager.testLogger;

import java.time.Duration;
import java.util.logging.Level;

import demo.pages.HomePage;
import demo.pages.SearchResultsPage;

public class TestCases extends BaseTest {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    /**
     * Test Case 01: Search for 'washing machine', sort by 'Popularity', and count items with ratings <= 4.
     */
    @Test(
        description = "Search for 'washing machine', sort by 'Popularity', and count items with ratings <= 4.",
        enabled = true,
        priority = 1
    )
    public void testCase01() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        testLogger(LogStatus.INFO, "Navigating to home page");
        homePage.navigateToHomePage();

        testLogger(LogStatus.INFO, "Searching for product 'Washing Machine'");
        homePage.searchForProduct("Washing Machine");

        testLogger(LogStatus.INFO, "Sorting results by 'Popularity'");
        searchResultsPage.sortBySelection("Popularity");

        testLogger(LogStatus.INFO, "Printing the number of items with rating <= 4");
        int itemCount = searchResultsPage.countItemsWithRatingLessThanOrEqualTo4();
        System.out.println("Number of items with rating <= 4: " + itemCount);
    }

    @Test(
        description = "Search for 'iPhone' and print the titles and discount % for items above 17% discount.",
        enabled = true,
        priority = 2
    )
    public void testCase02() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        testLogger(LogStatus.INFO, "Navigating to home page");
        homePage.navigateToHomePage();

        testLogger(LogStatus.INFO, "Searching for product 'iPhone'");
        homePage.searchForProduct("iPhone");

        testLogger(LogStatus.INFO, "Printing titles and discount percentages for items with discount > 17%");
        searchResultsPage.printTitleAndDiscountForEligibleItems();
    }

    @Test(
        description = "Search for 'Coffee Mug', filter by 4-star customer rating, and print top 5 reviewed items.",
        enabled = true,
        priority = 3
    )
    public void testCase03() {
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        testLogger(LogStatus.INFO, "Navigating to home page");
        homePage.navigateToHomePage();

        testLogger(LogStatus.INFO, "Searching for product 'Coffee Mug'");
        homePage.searchForProduct("Coffee Mug");

        testLogger(LogStatus.INFO, "Filtering results by 4-star customer rating");
        searchResultsPage.selectCustomerRatingChkBox("4");

        testLogger(LogStatus.INFO, "Printing top 5 items sorted by customer reviews");
        searchResultsPage.printTop5ItemsByReviews();
    }

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        if (driver != null) {
            driver.quit();
        }
    }
}