package pawaracademy.Resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject()

	{
		
		String filePath = System.getProperty("user.dir")+ "//reports//index.html";
				
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(filePath);
		extentSparkReporter.config().setReportName("Web Automation Result");
		extentSparkReporter.config().setDocumentTitle("Test Result");
		
		
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
		extentReport.setSystemInfo("Tester", "Mahesh Pawar");
		
		return extentReport;
	}

}
