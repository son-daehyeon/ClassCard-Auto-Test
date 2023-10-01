package com.github.ioloolo.classcard.my;

import lombok.Data;

@Data(staticConstructor = "of")
public final class MyWord {
	private final String english;
	private final String korean;
}
