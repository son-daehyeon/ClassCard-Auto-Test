package com.github.ioloolo.classcard;

import static com.github.ioloolo.classcard.factory.Account.Provider.User.*;
import static org.openqa.selenium.By.*;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.github.ioloolo.classcard.factory.Account;
import com.github.ioloolo.classcard.factory.Account.Provider;
import com.github.ioloolo.classcard.factory.Xpath;
import com.github.ioloolo.classcard.my.MyCardSet;
import com.github.ioloolo.classcard.my.MyClass;
import com.github.ioloolo.classcard.my.MyWord;

import lombok.SneakyThrows;

public final class ClassCard {
	public static void main(String[] args) {
		ClassCard classCard = new ClassCard();

		classCard.login(Provider.get(NAME));

		classCard.selectClass();
		classCard.selectCard();
		classCard.test();
	}

	private static final Scanner scanner = new Scanner(System.in);

	private final WebDriver driver;

	private MyClass selectedClass;
	private MyCardSet selectedCardSet;

	public ClassCard() {
		driver = new ChromeDriver();
	}

	@SneakyThrows
	private void login(Account account) {
		driver.get("https://www.classcard.net/Login");
		Thread.sleep(100);

		driver.findElement(Xpath.id()).sendKeys(account.getId());
		driver.findElement(Xpath.pw()).sendKeys(account.getPw());

		driver.findElement(Xpath.loginBtn()).click();
	}

	@SneakyThrows
	private void selectClass() {
		driver.get("https://www.classcard.net/Main");
		Thread.sleep(100);

		List<MyClass> classes = driver.findElement(Xpath.classCtn())
				.findElements(xpath(".//a"))
				.stream()
				.map(o -> MyClass.of(o.getText(), Integer.parseInt(o.getAttribute("href").substring(36))))
				.toList();

		System.out.println("========================================");
		for (int i = 0; i < classes.size(); i++) {
			System.out.println("  " + (i+1) + ". " + classes.get(i).getName());
		}
		System.out.println("========================================");

		System.out.print("클래스를 선택해주세요  : ");

		int input = scanner.nextInt();
		this.selectedClass = classes.get(input - 1);
	}

	@SneakyThrows
	private void selectCard() {
		driver.get("https://www.classcard.net/ClassMain/" + this.selectedClass.getId());
		Thread.sleep(100);

		List<MyCardSet> cardSets = driver.findElement(Xpath.cardContainer())
				.findElements(xpath(".//a[contains(@class, 'set-name-a')]"))
				.stream()
				.map(o -> MyCardSet.of(
						o.getText().replace(o.findElement(xpath(".//span")).getText(), ""),
						Integer.parseInt(o.getAttribute("data-idx")))
				)
				.toList();

		System.out.println("========================================");
		for (int i = 0; i < cardSets.size(); i++) {
			System.out.println("  " + (i+1) + ". " + cardSets.get(i).getName());
		}
		System.out.println("========================================");

		System.out.print("카드를 선택해주세요  : ");

		int input = scanner.nextInt();

		if (input == 0) {
			selectClass();
			return;
		}

		this.selectedCardSet = cardSets.get(input - 1);
	}

	@SneakyThrows
	public void test() {
		driver.get("https://www.classcard.net/set/%d/%d".formatted(selectedCardSet.getId(), selectedClass.getId()));
		Thread.sleep(100);

		this.selectedCardSet.fetch(driver);

		driver.get("https://www.classcard.net/ClassTest/%d/%d".formatted(selectedClass.getId(), selectedCardSet.getId()));
		Thread.sleep(100);

		driver.findElement(Xpath.testNextBtn()).click();
		driver.findElement(Xpath.testStartBtn()).click();

		while (true) {
			try {
				new Actions(driver).sendKeys(Keys.SPACE).build().perform();
			} catch (Throwable ignored) {}

			try {
				String problem = driver.findElement(Xpath.testFormEnglishText()).getText();

				if (problem == null || problem.isEmpty()) {
					continue;
				}

				MyWord answer = selectedCardSet.getWords()
						.stream()
						.filter(o -> o.getEnglish().equals(problem) || o.getKorean().equals(problem))
						.findFirst()
						.orElse(MyWord.of("", ""));

				driver.findElement(Xpath.testFormWordListCtn())
						.findElements(xpath(".//div[contains(@class, 'cc-table')]"))
						.stream()
						.filter(o -> o.getText().equals(answer.getEnglish()) || o.getText().equals(answer.getKorean()))
						.findFirst()
						.ifPresent(WebElement::click);
			} catch (Throwable ignored) {}
		}
	}
}
