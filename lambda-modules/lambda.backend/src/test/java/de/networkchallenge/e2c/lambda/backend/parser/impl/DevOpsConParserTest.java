package de.networkchallenge.e2c.lambda.backend.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;

import org.jsoup.Jsoup;
import org.junit.BeforeClass;
import org.junit.Test;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;

/**
 * @author Andreas
 *
 */
public class DevOpsConParserTest {
	private static URL URL;
	private static URL URL2;

	@BeforeClass
	public static void setup() {
		URL = Thread.currentThread().getContextClassLoader()
				.getResource("parserinput/" + DevOpsConParser.class.getSimpleName() + ".html");
		URL2 = Thread.currentThread().getContextClassLoader()
				.getResource("parserinput/" + DevOpsConParser.class.getSimpleName() + "2.html");
	}

	@Test
	public void parse() throws IOException {
		AbstractSuSMediaParser parser = new DevOpsConParser();
		CalendarEvent event = parser.parse(Jsoup.parse(new File(URL.getPath()), "UTF-8")).get(0);
		assertNotNull(event);
		assertEquals("DevSecOps Psychology", event.getTitle());
        assertEquals(
                ZonedDateTime.parse("2019-12-03T10:15:00+01:00[Europe/Berlin]"), event.getEventBegin());
		assertEquals("20191203T091500Z", event.getEventBeginGMT());
        assertEquals(
                ZonedDateTime.parse("2019-12-03T11:15:00+01:00[Europe/Berlin]"), event.getEventEnd());
		assertEquals("20191203T101500Z", event.getEventEndGMT());
	}

	@Test
	public void parse2() throws IOException {
		AbstractSuSMediaParser parser = new DevOpsConParser();
		CalendarEvent event = parser.parse(Jsoup.parse(new File(URL2.getPath()), "UTF-8")).get(0);
		assertNotNull(event);
		assertEquals("Kubernete-Architektur 101", event.getTitle());
        assertEquals(
                ZonedDateTime.parse("2018-05-29T11:45:00+02:00[Europe/Berlin]"), event.getEventBegin());
		assertEquals("20180529T094500Z", event.getEventBeginGMT());
        assertEquals(
                ZonedDateTime.parse("2018-05-29T12:45:00+02:00[Europe/Berlin]"), event.getEventEnd());
		assertEquals("20180529T104500Z", event.getEventEndGMT());
	}
}
