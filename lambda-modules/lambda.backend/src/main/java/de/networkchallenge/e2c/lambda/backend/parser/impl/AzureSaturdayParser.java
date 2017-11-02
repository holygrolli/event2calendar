package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.TimeParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Andreas
 *
 */
@Component
public class AzureSaturdayParser implements IEventParser {

	private final static TimeParser TP = new TimeParser("MMMM d, uuuu HH:mm xxx", Locale.US);

	public AzureSaturdayParser() {
	}

	private ZonedDateTime parseEnd(Document doc) {
		String date = doc.select("div.conference-date").text();
		String period = doc.select("div.conference-time").text();
		return TP.parse(date + " " + period.split("-")[1].replace("\u00a0", "").trim() + " +02:00");
	}

	private ZonedDateTime parseBegin(Document doc) {
		String date = doc.select("div.conference-date").text();
		String period = doc.select("div.conference-time").text();
		return TP.parse(date + " " + period.split("-")[0].replace("\u00a0", "").trim() + " +02:00");
	}

	private String parseTitle(Document doc) {
		Elements titles = doc.select("h1.conference_date");
		return titles.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
	}

	@Override
	public List<String> getBaseUris() {
		return Arrays.asList("azuresaturday.de");
	}

	@Override
	public List<CalendarEvent> parse(Document doc) {
		return Arrays.asList(new CalendarEvent(parseTitle(doc), parseBegin(doc), parseEnd(doc), "Microsoft München"));
	}
}
