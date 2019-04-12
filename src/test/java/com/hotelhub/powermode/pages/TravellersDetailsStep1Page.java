package com.hotelhub.powermode.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class TravellersDetailsStep1Page extends Base{
	
	
	

	public TravellersDetailsStep1Page()
	{
		PageFactory.initElements(driver(), this);
		
	}
	
	@FindBy(id="txtCustomerSearch")
	public WebElement TRAVELLER_DETAILS_INPUT;
	
	@FindBy(xpath="//h5[@class='text-danger m-0 p-0']")
	public WebElement TRAVELLER_DETAILS_PNR_GDS_ERROR;
	
	@FindBy(xpath=".//*[@id='divCustomerSearchResult']/a[1]")
	public WebElement FIRST_TRAVELLER;
	
	@FindBy(id="btnProceedDestinationSearch")
	public WebElement PROCEED_BUTTON;


	@FindBy(id="PNRnotValid")
	public WebElement SELECT_GDS;


	@FindBy(id="divCustomerSearchResult")
	public WebElement PREVIOUS_SEARCH;
	
	
}
