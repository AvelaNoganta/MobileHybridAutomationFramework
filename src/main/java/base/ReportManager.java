package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    //Ensure report always exists before use
    public static void initReports() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/Reports/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Platform", "Android");
            extent.setSystemInfo("Tester", "Avela");
            System.out.println("🧾 ExtentReports initialized at: " + reportPath);
        }
    }

    public static void createTest(String testName) {
        initReports(); //Ensure extent is never null
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
            System.out.println("ExtentReports flushed to file.");
        }
    }
}
