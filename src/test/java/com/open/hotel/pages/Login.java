package com.open.hotel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Login extends BasePage{

	@FindBy(how = How.ID, using = "username")
	WebElement UserName;
	@FindBy(how =How.ID, using = "password")
	WebElement Password;
	@FindBy(how =How.ID, using = "login")
	WebElement Login;
	@FindBy(how =How.ID, using = "/html/body/table[2]/tbody/tr[1]/td[2]/a[4]")
	WebElement LogOut;

	public Login(WebDriver driver){
		super(driver);
	}

	public void waitForLoad(){
		System.out.println("Login page");
	}

	public void pageIsValid(){
		System.out.println("Login page");
	}

	public void login(String userName, String password){
		driver().type(UserName, userName);
		driver().type(Password, password);
		driver().click(Login);
	}

	public void launchApp(String url) {
		driver().openUrl(url);
	}
}