package testPackage;

import org.openqa.selenium.By;

public class pageFactory {
	public By loginBtnLocator = By.className("login-button-large");
	public By logoutBtnLocator = By.linkText("Logout");	
	public By emailTxtLocator = By.name("email");
	public By passwordTxtLocator = By.name("password");
	public By submitLoginBtnLocator = By.className("sallab-submit-button");
	public By successLoginMsgLocator = By.className("Toastify__toast-body");
	public By menCategoryLocator = By.linkText("MEN");
}
