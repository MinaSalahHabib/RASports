package testPackage;

import org.testng.annotations.*;
import java.io.*; 
import java.util.*;  
public class smokeTestClass {
		
	PageOperation pageOperations;     
    pageFactory Locators;
    assertionStrings assertionStrs;
    Properties projectConfig; 
    
    @DataProvider(name = "login-data-provider")
    public static Object[][] FillLoginData()
    {
    	return new Object[][]{
    		{"eng.mina.salah.habib@gmail.com", "P@ssw0rd"},
    		//List of Users to login
    		};    	
    }
    
    public void GoToHomePage()
    {
    	pageOperations.GoToPage(projectConfig.getProperty("homePageURL"), assertionStrs.homePageTitle);    	  	  	
    }
    

    public void Login(String emailTxt,String passwordTxt)
    {    	    
    	
    	pageOperations.waitToClickOnBtn(Locators.loginBtnLocator);
    	
    	pageOperations.waitToSendKeysToTxtBox(Locators.emailTxtLocator, emailTxt);
    	
    	pageOperations.SendKeysToTxtBox(Locators.passwordTxtLocator, passwordTxt);
    	
    	pageOperations.ClickOnBtn(Locators.submitLoginBtnLocator);    	
    	
    	pageOperations.waitConfirmationMessage(Locators.successLoginMsgLocator, assertionStrs.successLoginMsg);    	    	    
    }
    
    public void Logout()
    {
    	pageOperations.waitToClickOnBtn(Locators.loginBtnLocator);
    	pageOperations.waitToClickOnBtn(Locators.logoutBtnLocator);
    }
    public void BrowseProducts()
    {
    	
    	pageOperations.waitToClickOnBtn(Locators.menCategoryLocator);        	   
    	pageOperations.waitForPageLoaded();
    	pageOperations.scrollDown();
    	pageOperations.waitForPageLoaded();
    }
	
    @Test(dataProvider= "login-data-provider")
  public void AddToCart(String emailTxt, String passwordTxt) {
		GoToHomePage();
		Login(emailTxt,passwordTxt);
		BrowseProducts();
	    AddItem();
  }
    private void AddItem() {
		// TODO Auto-generated method stub
		
	}

	@Test(dataProvider= "login-data-provider")
	public void RemoveFromCart(String emailTxt, String passwordTxt) {
		GoToHomePage();
		Login(emailTxt,passwordTxt);
		BrowseProducts();
		RemoveItem();
	  }
  private void RemoveItem() {
		// TODO Auto-generated method stub
		
	}

@BeforeClass
  public void beforeClass() {
	  Locators = new pageFactory();
	  assertionStrs =  new assertionStrings();
	  //projectConfig = new Configuration();
	  projectConfig = new Properties();
	  try {
		projectConfig.load(new FileReader("application.properties"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  pageOperations = new PageOperation(projectConfig.getProperty("chromeDriverRelativePath"), projectConfig.getProperty("chromeInstallationDir"));
	  
	  
  }

  @AfterClass
  public void afterClass() {
	  pageOperations.dispose();
  }

}
