package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.TimeParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Parser for jax.de<br>
 *     Dates are provided like this <pre>
 *     <span class="ws-label" style="font-weight: 600;">Freitag, 27. April 2018<br><span style="font-weight: 400;">09:00 - 17:00</span></span>
 *     </pre>
 */
public class JaxParser implements IEventParser {
    private final static TimeParser TP_DE = new TimeParser("d. MMMM uuuu HH:mm xxx", Locale.GERMANY);
    private final static TimeParser TP_US = new TimeParser("MMMM d uuuu HH:mm xxx", Locale.US);

    @Override
    public List<String> getBaseUris() {
        return Arrays.asList("jax.de");
    }

    @Override
    public List<CalendarEvent> parse(Document doc) {
        return Arrays.asList(new CalendarEvent(parseTitle(doc), parseBegin(doc), parseEnd(doc), ""));
    }

    private ZonedDateTime parseEnd(Document doc) {
        Elements sessionInfo = doc.select("span.ws-label");
        String date = sessionInfo.first().ownText().split(",")[1].trim();
        String period = sessionInfo.select("span").get(1).text();
        return parseLocaleDateTime(doc,date + " " + period.split(" - ")[1] + " +02:00");
    }

    private ZonedDateTime parseBegin(Document doc) {
        Elements sessionInfo = doc.select("span.ws-label");
        String date = sessionInfo.first().ownText().split(",")[1].trim();
        String period = sessionInfo.select("span").get(1).text();
        return parseLocaleDateTime(doc,date + " " + period.split(" - ")[0] + " +02:00");
    }

    private String parseTitle(Document doc) {
        Elements titles = doc.select("h2.gdlr-page-title");
        return titles.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
    }

    private ZonedDateTime parseLocaleDateTime(Document doc, String pattern)
    {
        if (doc.select("html").first().attr("lang").startsWith("en"))
            return TP_US.parse(pattern);
        else
            return TP_DE.parse(pattern);
    }
}
