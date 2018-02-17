package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.SuSMediaDateParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Base clase for S&S Media Events with "old" layout.<br>
 * Dates are provided like this <pre>
 *     <div class="gdlr-session-info"><div class="session-info session-time"><i class="fa fa-calendar"></i>11 Apr 2018</div>
 *       <div class="session-info session-time"><i class="fa fa-clock-o"></i>11:15 - 12:00</div>
 *     </pre>
 */
public abstract class AbstractSuSMediaOldLayoutParser implements IEventParser {
    @Override
    public abstract List<String> getBaseUris();

    @Override
    public List<CalendarEvent> parse(Document doc) {
        return Collections.singletonList(new CalendarEvent(parseTitle(doc), parseBegin(doc), parseEnd(doc), getLocation()));
    }

    private String parseTitle(Document doc) {
        Elements titles = doc.select("h1.gdlr-session-title");
        return titles.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
    }

    private ZonedDateTime parseBegin(Document doc) {
        Elements sessionInfo = doc.select(".gdlr-session-info");
        String date = sessionInfo.select(".session-time:has(.fa-calendar)").first().text();
        String period = sessionInfo.select(".session-time:has(.fa-clock-o)").first().text();
        return SuSMediaDateParser.parseDateTimeOldLayout(date + " " + period.split(" - ")[0], getZoneId());
    }

    private ZonedDateTime parseEnd(Document doc) {
        Elements sessionInfo = doc.select(".gdlr-session-info");
        String date = sessionInfo.select(".session-time:has(.fa-calendar)").first().text();
        String period = sessionInfo.select(".session-time:has(.fa-clock-o)").first().text();
        return SuSMediaDateParser.parseDateTimeOldLayout(date + " " + period.split(" - ")[1], getZoneId());
    }

    /**
     * @return the location of the event as {@link String}
     */
    protected abstract String getLocation();

    abstract ZoneId getZoneId();
}
