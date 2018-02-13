package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.SuSMediaDateParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Base clase for S&S Media Events.<br>
 * Dates are provided like this <pre>
 *     <span class="ws-label" style="font-weight: 600;">Freitag, 27. April 2018<br><span style="font-weight: 400;">09:00 - 17:00</span></span>
 *     </pre>
 */
public abstract class AbstractSuSMediaParser implements IEventParser {
    @Override
    public abstract List<String> getBaseUris();

    @Override
    public List<CalendarEvent> parse(Document doc) {
        return Collections.singletonList(new CalendarEvent(parseTitle(doc), parseBegin(doc), parseEnd(doc), getLocation()));
    }

    private String parseTitle(Document doc) {
        Elements titles = doc.select("h2.gdlr-page-title");
        return titles.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
    }

    private ZonedDateTime parseBegin(Document doc) {
        Elements sessionInfo = doc.select("span.ws-label");
        String date = sessionInfo.first().ownText().split(",")[1].trim();
        String period = sessionInfo.select("span").get(1).text();
        return SuSMediaDateParser.parseLocaleDateTime(doc, date + " " + period.split(" - ")[0] + " +02:00");
    }

    private ZonedDateTime parseEnd(Document doc) {
        Elements sessionInfo = doc.select("span.ws-label");
        String date = sessionInfo.first().ownText().split(",")[1].trim();
        String period = sessionInfo.select("span").get(1).text();
        return SuSMediaDateParser.parseLocaleDateTime(doc, date + " " + period.split(" - ")[1] + " +02:00");
    }

    /**
     * @return the location of the event as {@link String}
     */
    protected abstract String getLocation();
}
