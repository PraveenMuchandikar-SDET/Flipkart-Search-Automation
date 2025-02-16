package demo;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.LogStatus;

import static demo.Utils.ExtentManager.*;
import static demo.Utils.ExtentTestManager.*;


public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void beforeTestMethod(Method method) {
        startTest(method.getName());
    }

    @AfterMethod
    public void afterTestMethod(ITestResult iTestResult){
        if (iTestResult.getStatus() == ITestResult.SUCCESS) {
            testLogger(LogStatus.PASS, "Test Passed");
        } else if (iTestResult.getStatus() == ITestResult.FAILURE) {
            testLogger(LogStatus.FAIL, "Test Failed");
        } else if (iTestResult.getStatus() == ITestResult.SKIP) {
            testLogger(LogStatus.SKIP, "Test Skipped");
        } 
        endTest();
    }

    @AfterSuite
    public void tearDownSuite()
    {
        getExtentReports().flush();
    }
}
