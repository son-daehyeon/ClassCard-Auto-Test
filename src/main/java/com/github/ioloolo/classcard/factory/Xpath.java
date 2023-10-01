package com.github.ioloolo.classcard.factory;

import org.openqa.selenium.By;

public final class Xpath {

	public static By id() {
		return By.xpath("//*[@id=\"login_id\"]");
	}

	public static By pw() {
		return By.xpath("//*[@id=\"login_pwd\"]");
	}

	public static By loginBtn() {
		return By.xpath("//*[@id=\"loginForm\"]/div[3]/button");
	}

	public static By classCtn() {
		return By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div[3]/div[2]");
	}

	public static By cardContainer() {
		return By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div[3]/div");
	}

	public static By testNextBtn() {
		return By.xpath("//*[@id=\"wrapper-test\"]/div/div[1]/div[1]/div[4]/a");
	}

	public static By testStartBtn() {
		return By.xpath("//*[@id=\"wrapper-test\"]/div/div[1]/div[3]/div[3]/a");
	}

	public static By testFormEnglishText() {
		return By.xpath("//div[contains(@class, 'showing')]//div[contains(@class, 'cc-table')]");
	}

	public static By testFormWordListCtn() {
		return By.xpath("//div[contains(@class, 'showing')]//div[contains(@class, 'cc-radio-box-body')]");
	}
}
