package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import org.jsoup.Jsoup;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Andreas
 *
 */
public class AzureSaturdayParserTest {
	private static URL URL;
	private static URL URL2;

	@BeforeClass
	public static void setup() {
		URL = Thread.currentThread().getContextClassLoader()
				.getResource("parserinput/" + AzureSaturdayParser.class.getSimpleName() + ".html");
		URL2 = Thread.currentThread().getContextClassLoader()
				.getResource("parserinput/" + AzureSaturdayParser.class.getSimpleName() + "2.html");
	}

	@Test
	public void parse() throws MalformedURLException, IOException {
		IEventParser parser = new AzureSaturdayParser();
		CalendarEvent event = parser.parse(Jsoup.parse(new File(URL.getPath()), "UTF-8")).get(0);
		assertNotNull(event);
		assertEquals("Keynote: 0 to DevOps", event.getTitle());
		assertEquals(ZonedDateTime.parse("2017-06-24T09:20:00+02:00"), event.getEventBegin());
		assertEquals("20170624T072000Z", event.getEventBeginGMT());
		assertEquals(ZonedDateTime.parse("2017-06-24T10:10:00+02:00"), event.getEventEnd());
		assertEquals("20170624T081000Z", event.getEventEndGMT());
	}

	@Test
	public void parse2() throws MalformedURLException, IOException {
		IEventParser parser = new AzureSaturdayParser();
		CalendarEvent event = parser.parse(Jsoup.parse(new File(URL2.getPath()), "UTF-8")).get(0);
		assertNotNull(event);
		assertEquals("Planning for the Cloud – Cloud Readiness and Cloud Roles", event.getTitle());
		assertEquals(ZonedDateTime.parse("2017-06-24T10:30:00+02:00"), event.getEventBegin());
		assertEquals("20170624T083000Z", event.getEventBeginGMT());
		assertEquals(ZonedDateTime.parse("2017-06-24T11:20:00+02:00"), event.getEventEnd());
		assertEquals("20170624T092000Z", event.getEventEndGMT());
	}
}
