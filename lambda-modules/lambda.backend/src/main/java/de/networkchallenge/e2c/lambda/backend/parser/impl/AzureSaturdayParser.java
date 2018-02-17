package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.TimeParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author Andreas
 *
 */
public class AzureSaturdayParser implements IEventParser {

    private final static TimeParser TP = new TimeParser("MMMM d, uuuu HH:mm", Locale.US);

	@Override
	public List<String> getBaseUris() {
		return Collections.singletonList("azuresaturday.de");
	}

	@Override
	public List<CalendarEvent> parse(Document doc) {
		return Collections.singletonList(new CalendarEvent(parseTitle(doc), parseBegin(doc), parseEnd(doc), "Microsoft München"));
	}

	private String parseTitle(Document doc) {
		Elements titles = doc.select("h1.conference_date");
		return titles.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
	}

	private ZonedDateTime parseBegin(Document doc) {
		String date = doc.select("div.conference-date").text();
		String period = doc.select("div.conference-time").text();
        return TP.parse(date + " " + period.split("-")[0].replace("\u00a0", "").trim(), ZoneId.of("Europe/Berlin"));
    }

	private ZonedDateTime parseEnd(Document doc) {
		String date = doc.select("div.conference-date").text();
		String period = doc.select("div.conference-time").text();
        return TP.parse(date + " " + period.split("-")[1].replace("\u00a0", "").trim(), ZoneId.of("Europe/Berlin"));
    }
}
