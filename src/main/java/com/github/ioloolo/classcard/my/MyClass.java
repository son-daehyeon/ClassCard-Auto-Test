package com.github.ioloolo.classcard.my;

import lombok.Data;

@Data(staticConstructor = "of")
public final class MyClass {
	private final String name;
	private final int id;
}
