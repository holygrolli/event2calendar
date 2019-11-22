package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.TimeParser;
import org.jsoup.nodes.Document;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DemoParser implements IEventParser {
    private final static TimeParser TP = new TimeParser("dd. MMMM yyyy HH:mm", Locale.GERMANY);

    @Override
    public List<String> getBaseUris() {
        return Arrays.asList("e2c.networkchallenge.de");
    }

    @Override
    public List<CalendarEvent> parse(Document doc) {
        String title = doc.select("h1").first().text();
        String location = doc.getElementById("location").text();
        String basedate = doc.getElementById("date").text();
        String start = doc.getElementById("start").text().split(" ")[0];
        String end = doc.getElementById("end").text().split(" ")[0];

        return Arrays.asList(new CalendarEvent(title, TP.parse(basedate + " " + start, ZoneId.of("Europe/Berlin")),
                TP.parse(basedate + " " + end, ZoneId.of("Europe/Berlin")), location));
    }
}
