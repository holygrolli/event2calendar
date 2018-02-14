package de.networkchallenge.e2c.lambda.backend.parser.impl.util;

import org.jsoup.nodes.Document;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

public class SuSMediaDateParser {
    private final static TimeParser TP_DE = new TimeParser("d. MMMM uuuu HH:mm", Locale.GERMANY);
    private final static TimeParser TP_US = new TimeParser("MMMM d uuuu HH:mm", Locale.US);

    public static ZonedDateTime parseLocaleDateTime(Document doc, String date, ZoneId timezone)
    {
        if (doc.select("html").first().attr("lang").startsWith("en"))
            return TP_US.parse(date, timezone);
        else
            return TP_DE.parse(date, timezone);
    }
}
