package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class JaxParserTest {

    private static java.net.URL URL;
    private static URL URL2;
    private static URL URL_EN;

    @BeforeAll
    public static void setup() {
        URL = Thread.currentThread().getContextClassLoader()
                .getResource("parserinput/" + JaxParser.class.getSimpleName() + ".html");
        URL2 = Thread.currentThread().getContextClassLoader()
                .getResource("parserinput/" + JaxParser.class.getSimpleName() + "2.html");
        URL_EN = Thread.currentThread().getContextClassLoader()
                .getResource("parserinput/" + JaxParser.class.getSimpleName() + "En.html");
    }
    @Test
    public void getBaseUris() throws Exception {
        AbstractSuSMediaParser parser = new JaxParser();
        assertTrue(parser.getBaseUris().contains("jax.de"));
        assertTrue(parser.getBaseUris().size() == 1);

    }

    @Test
    public void parse() throws Exception {
        JaxParser parser = new JaxParser();
        CalendarEvent event = parser.parse(Jsoup.parse(new File(URL.getPath()), "UTF-8")).get(0);
        assertNotNull(event);
        assertEquals("The unshippable Product Increment", event.getTitle());
        assertEquals(ZonedDateTime.parse("2018-04-24T09:45:00+02:00[Europe/Berlin]"), event.getEventBegin());
        assertEquals("20180424T074500Z", event.getEventBeginGMT());
        assertEquals(ZonedDateTime.parse("2018-04-24T10:45:00+02:00[Europe/Berlin]"), event.getEventEnd());
        assertEquals("20180424T084500Z", event.getEventEndGMT());
    }
    @Test
    public void parse2() throws Exception {
        JaxParser parser = new JaxParser();
        CalendarEvent event = parser.parse(Jsoup.parse(new File(URL2.getPath()), "UTF-8")).get(0);
        assertNotNull(event);
        assertEquals("Architektur Retreat Workshop", event.getTitle());
        assertEquals(ZonedDateTime.parse("2018-04-27T09:00:00+02:00[Europe/Berlin]"), event.getEventBegin());
        assertEquals("20180427T070000Z", event.getEventBeginGMT());
        assertEquals(ZonedDateTime.parse("2018-04-27T17:00:00+02:00[Europe/Berlin]"), event.getEventEnd());
        assertEquals("20180427T150000Z", event.getEventEndGMT());
    }

    @Test
    public void parseEn() throws Exception {
        JaxParser parser = new JaxParser();
        CalendarEvent event = parser.parse(Jsoup.parse(new File(URL_EN.getPath()), "UTF-8")).get(0);
        assertNotNull(event);
        assertEquals("The unshippable Product Increment", event.getTitle());
        assertEquals(ZonedDateTime.parse("2018-04-24T09:45:00+02:00[Europe/Berlin]"), event.getEventBegin());
        assertEquals("20180424T074500Z", event.getEventBeginGMT());
        assertEquals(ZonedDateTime.parse("2018-04-24T10:45:00+02:00[Europe/Berlin]"), event.getEventEnd());
        assertEquals("20180424T084500Z", event.getEventEndGMT());
    }

}