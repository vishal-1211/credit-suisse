package com.credit.suisse.constants;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogConstants {

	private LogConstants() {
		// private constructor for constants class
	}

	public static final String BASE_PACKAGE = "com.credit.suisse";
	public static final String SWAGGER_BASE_PACKAGE = "com.credit.suisse.controller";
	public static final Logger INFO_LOGGER = Logger.getLogger(Level.INFO.getName());

}
