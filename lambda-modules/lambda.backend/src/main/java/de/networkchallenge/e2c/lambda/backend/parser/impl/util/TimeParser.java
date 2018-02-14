package de.networkchallenge.e2c.lambda.backend.parser.impl.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Andreas
 *
 */
public class TimeParser {
	private String pattern;
	private Locale sourceLocale;

	public TimeParser(String pattern, Locale sourceLocale) {
		this.pattern = pattern;
		this.sourceLocale = sourceLocale;
	}

	public ZonedDateTime parse(String input, ZoneId timezone) {
		return ZonedDateTime.parse(input, DateTimeFormatter.ofPattern(pattern, sourceLocale).withZone(timezone));
	}
}
