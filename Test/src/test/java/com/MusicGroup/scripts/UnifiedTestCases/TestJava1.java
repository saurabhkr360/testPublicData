package test.java.com.MusicGroup.scripts.UnifiedTestCases;
import org.testng.annotations.Test;

import test.java.com.MusicGroup.util.DriverTestCase;
import test.java.com.MusicGroup.util.ExecutionLog;
import test.java.com.MusicGroup.util.PropertyReader;
import test.java.com.MusicGroup.pagehelper.Unified_OrderSparePartsHelper;
public class TestJava1 extends DriverTestCase{
	@Test
	public void TestJava1()throws Exception{
		try{
			unified_OrderSparePartsHelper = new Unified_OrderSparePartsHelper(driver);
			propertyReader = new PropertyReader();
			ExecutionLog.LogAddClass(this.getClass().getName() + " and Test method " +Thread.currentThread().getStackTrace()[1].getMethodName());		
			unified_OrderSparePartsHelper.VerifyAuthorizedPartnerPagePopup();
			ExecutionLog.Log("Unified Order Spare parts.");
		}
		catch(Error e) 
		{
			captureScreenshot("Unified_OrderSpareParts");	
			ExecutionLog.LogErrorMessage(e);
			throw e;
		}
		catch(Exception ex) 
		{
			captureScreenshot("Unified_OrderSpareParts");	
			ExecutionLog.LogExceptionMessage(ex);	
			throw ex;

		}
		}
}
