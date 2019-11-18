package de.networkchallenge.e2c.lambda.backend.parser;

import de.networkchallenge.e2c.lambda.backend.parser.impl.util.TimeParser;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeParserTest {

	@Test
	public void parsetest() {
		assertEquals(ZonedDateTime.parse("2017-06-14T10:15:00+02:00[Europe/Berlin]"),
				new TimeParser("d MMM yyyy HH:mm", Locale.US).parse("14 Jun 2017 10:15", ZoneId.of("Europe/Berlin")));
		assertEquals(ZonedDateTime.parse("2017-06-14T10:15:00+02:00[Europe/Berlin]"),
				new TimeParser("MMMM d, uuuu HH:mm", Locale.US).parse("June 14, 2017 10:15", ZoneId.of("Europe/Berlin")));
	}

	@Test
	public void commonDateConversion() {
		assertEquals("June 24, 2017 10:15 +02:00", ZonedDateTime.parse("2017-06-24T10:15:00+02:00")
				.format(DateTimeFormatter.ofPattern("MMMM d, uuuu HH:mm xxx", Locale.US)));
		assertEquals(ZonedDateTime.parse("2017-06-24T10:15:00+02:00"), ZonedDateTime.parse("June 24, 2017 10:15 +02:00",
				DateTimeFormatter.ofPattern("MMMM d, uuuu HH:mm xxx", Locale.US)));
	}
}
