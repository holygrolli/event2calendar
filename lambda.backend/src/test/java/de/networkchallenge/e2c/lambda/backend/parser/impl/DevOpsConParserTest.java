package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
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
	public void parse() throws MalformedURLException, IOException {
		DevOpsConParser parser = new DevOpsConParser();
		CalendarEvent event = parser.parse(Jsoup.parse(new File(URL.getPath()), "UTF-8")).get(0);
		assertNotNull(event);
		assertEquals("Docker Container Loading", event.getTitle());
		assertEquals(ZonedDateTime.parse("2017-06-14T10:15:00+02:00"), event.getEventBegin());
		assertEquals("20170614T081500Z", event.getEventBeginGMT());
		assertEquals(ZonedDateTime.parse("2017-06-14T11:15:00+02:00"), event.getEventEnd());
		assertEquals("20170614T091500Z", event.getEventEndGMT());
	}

	@Test
	public void parse2() throws MalformedURLException, IOException {
		DevOpsConParser parser = new DevOpsConParser();
		CalendarEvent event = parser.parse(Jsoup.parse(new File(URL2.getPath()), "UTF-8")).get(0);
		assertNotNull(event);
		assertEquals("Web Hacking: Pentesting and attacking Web Apps (sold out)", event.getTitle());
		assertEquals(ZonedDateTime.parse("2017-06-12T09:30:00+02:00"), event.getEventBegin());
		assertEquals("20170612T073000Z", event.getEventBeginGMT());
		assertEquals(ZonedDateTime.parse("2017-06-12T17:30:00+02:00"), event.getEventEnd());
		assertEquals("20170612T153000Z", event.getEventEndGMT());
	}
}
