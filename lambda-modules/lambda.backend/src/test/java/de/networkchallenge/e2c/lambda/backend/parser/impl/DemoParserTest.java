package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DemoParserTest {

    private static URL URL = Thread.currentThread().getContextClassLoader()
            .getResource("parserinput/" + DemoParser.class.getSimpleName() + ".html");

    @Test
    void getBaseUris() {
        assertTrue(new DemoParser().getBaseUris().contains("e2c.networkchallenge.de"));
    }

    @Test
    void parse() throws IOException {
        DemoParser parser = new DemoParser();
        List<CalendarEvent> calendarEvents = parser.parse(Jsoup.parse(new File(URL.getPath()), "UTF-8"));
        assertEquals("Exciting Event", calendarEvents.get(0).getTitle());
        assertEquals("tba", calendarEvents.get(0).getLocation());
        assertEquals("20210412T180000Z", calendarEvents.get(0).getEventBeginGMT());
        assertEquals("20210412T200000Z", calendarEvents.get(0).getEventEndGMT());
    }
}