package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import org.jsoup.Jsoup;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiConferenceParserTest {

    private static java.net.URL URL;

    @BeforeClass
    public static void setup() {
        URL = Thread.currentThread().getContextClassLoader()
                .getResource("parserinput/" + ApiConferenceParser.class.getSimpleName() + ".html");
    }

    @Test
    public void parse() throws Exception {
        IEventParser parser = new ApiConferenceParser();
        CalendarEvent event = parser.parse(Jsoup.parse(new File(URL.getPath()), "UTF-8")).get(0);
        assertNotNull(event);
        assertEquals("Why Do Microservices Need an API Gateway?", event.getTitle());
        assertEquals(ZonedDateTime.parse("2018-04-11T11:15:00+01:00[Europe/London]"), event.getEventBegin());
        assertEquals("20180411T101500Z", event.getEventBeginGMT());
        assertEquals(ZonedDateTime.parse("2018-04-11T12:00:00+01:00[Europe/London]"), event.getEventEnd());
        assertEquals("20180411T110000Z", event.getEventEndGMT());
    }

}