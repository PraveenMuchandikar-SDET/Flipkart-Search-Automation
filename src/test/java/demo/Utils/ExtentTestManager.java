package demo.Utils;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentTestManager {
    static ExtentReports extentReports = ExtentManager.getExtentReports();
    static Map<Object, Object> extentMap = new HashMap<>();

    public static ExtentTest startTest(String testName){
        ExtentTest extentTest = extentReports.startTest(testName);
        extentMap.put((long)Thread.currentThread().getId(), extentTest);
        return extentTest;
    }

    public static ExtentTest gettest(){
        return (ExtentTest)extentMap.get((long)Thread.currentThread().getId());
    }

    public static void testLogger(LogStatus status, String logDescription){
        gettest().log(status, logDescription);
    }

    public static void endTest(){
        extentReports.endTest(gettest());
    }
}
