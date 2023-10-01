package com.github.ioloolo.classcard.factory;

import lombok.Data;

@Data(staticConstructor = "of")
public final class Account {

	private final String id;
	private final String pw;

	public static final class Provider {
		public static Account get(User user) {
			return switch (user) {
				case NAME -> Account.of("ID", "PW");
			};
		}

		public enum User {
			NAME
		}
	}
}
