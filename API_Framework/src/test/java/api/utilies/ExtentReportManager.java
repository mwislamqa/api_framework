package api.utilies;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext testContext)
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName= "Test-Report-"+ timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ reportName);
		sparkReporter.config().setDocumentTitle("api_automation");
		sparkReporter.config().setReportName("API_TestNG_Frameword");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("application", "api test");
		extent.setSystemInfo("operation System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("User", "Md Islam");
		extent.setSystemInfo("ENV", "QA");
		
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
}
