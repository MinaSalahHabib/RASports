package testPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageOperation {
	public WebDriver driver ;
	WebDriverWait wait;
	int loadingTimeout = 30; 
	PageOperation(String chromeDriverRelativePath, String chromeInstallationDir)
	{
		String driverPath = System.getProperty("user.dir") + chromeDriverRelativePath;
		  ChromeOptions options = new ChromeOptions();
		  	
	      options.setBinary(chromeInstallationDir);

	      options.setExperimentalOption("useAutomationExtension", false);
	      System.setProperty("webdriver.chrome.driver", driverPath);     
	      driver = new ChromeDriver(options);
	      wait = new WebDriverWait(driver,loadingTimeout);
	      driver.manage().window().maximize();
	}
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, loadingTimeout);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
    
	public void GoToPage(String pageUrl, String assertionStr)
    {
    	driver.get(pageUrl);    	
    	waitForPageLoaded();
  	  	Assert.assertEquals(driver.getTitle(), assertionStr);  	  	
    }
	
	public void scrollDown()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,500)"); 
	}
	
	public void waitToClickOnBtn(By locator)
	{
		wait.until(ExpectedConditions.elementToBeClickable(locator));
    	WebElement Btn = driver.findElement(locator);
    	Btn.click();
	}
	public void ClickOnBtn(By locator)
	{
    	WebElement menBtn = driver.findElement(locator);
    	menBtn.click();
	}
	
	public void waitToSendKeysToTxtBox(By locator,String text)
	{
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement txtBox = driver.findElement(locator);
    	txtBox.sendKeys(text);
	}
	public void SendKeysToTxtBox(By locator,String text)
	{
    	WebElement txtBox = driver.findElement(locator);
    	txtBox.sendKeys(text);
	}
	
	public void waitConfirmationMessage(By locator,String txtMsg)
	{
		wait.until(ExpectedConditions.textToBe(locator, txtMsg ));
	}
	public void dispose()
	{
		driver.close();
		driver.quit();
	}
}
