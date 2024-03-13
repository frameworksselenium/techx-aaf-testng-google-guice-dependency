package com.open.hotel.pages;

import com.techx.core.context.BaseWdPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class Search extends BaseWdPage {

	String testCaseName = null;
	String testCaseID = null;
	WebDriverWait wait = null;
	WebDriver driver = null;
	org.apache.log4j.Logger log = null;
	String pageName = "Hotel Search Page";

	public Search(WebDriver driver){
		super(driver);
	}

	public void waitForLoad(){
		System.out.println("Login page");
	}

	public void pageIsValid(){
		System.out.println("Login page");
	}

	@FindBy(how = How.NAME, using = "location")
	WebElement Location;
	@FindBy(how =How.NAME, using = "room_nos")
	WebElement NoOfRoom;
	@FindBy(how =How.NAME, using = "datepick_in")
	WebElement DatepickIn;
	@FindBy(how =How.NAME, using = "datepick_out")
	WebElement DatepickOut;
	@FindBy(how =How.NAME, using = "adult_room")
	WebElement AdultRoom;
	@FindBy(how =How.NAME, using = "Submit")
	WebElement Submit;
	@FindBy(how =How.NAME, using = "//*[contains(text(),'Select Hotel')]")
	WebElement SelectHotelText;

	public void enterRoomSearchInfo(HashMap<String, String> values) {

	}

}