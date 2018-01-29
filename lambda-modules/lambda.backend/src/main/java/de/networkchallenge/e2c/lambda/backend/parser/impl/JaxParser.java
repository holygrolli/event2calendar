package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class JaxParser implements IEventParser {
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
        return ZonedDateTime.parse(date + " " + period.split(" - ")[1] + " +02:00",
                DateTimeFormatter.ofPattern("d. MMMM uuuu HH:mm xxx").withLocale(Locale.GERMANY));
    }

    private ZonedDateTime parseBegin(Document doc) {
        Elements sessionInfo = doc.select("span.ws-label");
        String date = sessionInfo.first().ownText().split(",")[1].trim();
        String period = sessionInfo.select("span").get(1).text();
        return ZonedDateTime.parse(date + " " + period.split(" - ")[0] + " +02:00",
                DateTimeFormatter.ofPattern("d. MMMM uuuu HH:mm xxx").withLocale(Locale.GERMANY));
    }

    private String parseTitle(Document doc) {
        Elements titles = doc.select("h2.gdlr-page-title");
        return titles.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
    }
}
