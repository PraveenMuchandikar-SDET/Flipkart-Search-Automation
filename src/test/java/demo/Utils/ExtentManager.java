package demo.Utils;

import java.io.File;

import com.relevantcodes.extentreports.*;

public class ExtentManager {
    static ExtentReports extentReports;

    final static String reportPath = System.getProperty("user.dir") + File.separator + "ExtentReports" + File.separator + "completeTestReports.html";
    final static String CONFIG_FILE_PATH = System.getProperty("user.dir") + File.separator + "extent_customization_configs.xml";


    public static synchronized ExtentReports getExtentReports() {
        if (extentReports == null){
            extentReports = new ExtentReports(reportPath, true);
            extentReports.loadConfig(new File(CONFIG_FILE_PATH));
        }
        return extentReports;
    }
    
    
}
