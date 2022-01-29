package com.credit.suisse.enums;

public enum State {

	STARTED, FINISHED;

	public static String getOppositeStateName(String stateName) {
		return STARTED.name().equals(stateName) ? FINISHED.name() : STARTED.name();
	}
}
