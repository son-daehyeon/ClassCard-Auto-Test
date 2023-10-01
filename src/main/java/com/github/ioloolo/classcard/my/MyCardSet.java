package com.github.ioloolo.classcard.my;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import lombok.Data;
import lombok.Getter;

@Data(staticConstructor = "of")
public final class MyCardSet {
	private final String name;
	private final int id;

	@Getter
	private List<MyWord> words;

	public void fetch(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		//noinspection unchecked
		List<Map<String, ?>> studyData = (List<Map<String, ?>>) js.executeScript("return study_data");

		this.words = studyData.stream()
				.map(wordData -> MyWord.of((String) wordData.get("front_data"), (String) wordData.get("back_data")))
				.toList();

		System.out.println(this.words);
	}
}
