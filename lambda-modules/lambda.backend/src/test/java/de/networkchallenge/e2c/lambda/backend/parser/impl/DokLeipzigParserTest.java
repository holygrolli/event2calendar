package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import org.jsoup.Jsoup;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DokLeipzigParserTest {
    private static java.net.URL URL;
    private static URL URL2;

    @BeforeClass
    public static void setup() {
        URL = Thread.currentThread().getContextClassLoader()
                .getResource("parserinput/" + DokLeipzigParser.class.getSimpleName() + ".html");
        URL2 = Thread.currentThread().getContextClassLoader()
                .getResource("parserinput/" + DokLeipzigParser.class.getSimpleName() + "2.html");
    }

    @Test
    public void parse() throws IOException {
        DokLeipzigParser parser = new DokLeipzigParser();
        List<CalendarEvent> calendarEvents = parser.parse(Jsoup.parse(new File(URL.getPath()), "UTF-8"));
        assertNotNull(calendarEvents.get(0));
        assertEquals("Talking Money", calendarEvents.get(0).getTitle());
        assertEquals(ZonedDateTime.parse("2017-11-02T16:15:00+01:00[Europe/Berlin]"), calendarEvents.get(0).getEventBegin());
        assertEquals("20171102T151500Z", calendarEvents.get(0).getEventBeginGMT());
        assertEquals(ZonedDateTime.parse("2017-11-02T17:45:00+01:00[Europe/Berlin]"), calendarEvents.get(0).getEventEnd());
        assertEquals("20171102T164500Z", calendarEvents.get(0).getEventEndGMT());
        assertEquals("CineStar 4", calendarEvents.get(0).getLocation());
        assertNotNull(calendarEvents.get(1));
        assertEquals("Talking Money", calendarEvents.get(1).getTitle());
        assertEquals(ZonedDateTime.parse("2017-11-04T19:45:00+01:00[Europe/Berlin]"), calendarEvents.get(1).getEventBegin());
        assertEquals("20171104T184500Z", calendarEvents.get(1).getEventBeginGMT());
        assertEquals(ZonedDateTime.parse("2017-11-04T21:15:00+01:00[Europe/Berlin]"), calendarEvents.get(1).getEventEnd());
        assertEquals("20171104T201500Z", calendarEvents.get(1).getEventEndGMT());
        assertEquals("CineStar 4", calendarEvents.get(1).getLocation());
    }

    @Test
    public void parse2() throws IOException {
        DokLeipzigParser parser = new DokLeipzigParser();
        List<CalendarEvent> calendarEvents = parser.parse(Jsoup.parse(new File(URL2.getPath()), "UTF-8"));
        assertNotNull(calendarEvents.get(0));
        assertEquals("Betrug", calendarEvents.get(0).getTitle());
        assertEquals(ZonedDateTime.parse("2017-10-30T19:00:00+02:00[Europe/Berlin]"), calendarEvents.get(0).getEventBegin());
        assertEquals("20171030T180000Z", calendarEvents.get(0).getEventBeginGMT());
        assertEquals(ZonedDateTime.parse("2017-10-30T20:30:00+02:00[Europe/Berlin]"), calendarEvents.get(0).getEventEnd());
        assertEquals("20171030T193000Z", calendarEvents.get(0).getEventEndGMT());
        assertEquals("CineStar 8", calendarEvents.get(0).getLocation());
        assertNotNull(calendarEvents.get(1));
        assertEquals("Betrug", calendarEvents.get(1).getTitle());
        assertEquals(ZonedDateTime.parse("2017-10-30T19:00:00+02:00[Europe/Berlin]"), calendarEvents.get(1).getEventBegin());
        assertEquals("20171030T180000Z", calendarEvents.get(1).getEventBeginGMT());
        assertEquals(ZonedDateTime.parse("2017-10-30T20:30:00+02:00[Europe/Berlin]"), calendarEvents.get(1).getEventEnd());
        assertEquals("20171030T193000Z", calendarEvents.get(1).getEventEndGMT());
        assertEquals("CineStar 6", calendarEvents.get(1).getLocation());
        assertEquals("Hauptbahnhof Osthalle", calendarEvents.get(2).getLocation());
        assertEquals(5, calendarEvents.size());
    }

}