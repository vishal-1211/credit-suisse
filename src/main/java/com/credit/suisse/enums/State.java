package com.credit.suisse.enums;

import java.util.stream.Stream;

public enum State {

	STARTED, FINISHED;

	public static State getStateName(String stateName) {
		return Stream.of(State.values()).filter(data -> data.name().equals(stateName)).map(data -> data).findFirst()
				.orElse(null);
	}
}
