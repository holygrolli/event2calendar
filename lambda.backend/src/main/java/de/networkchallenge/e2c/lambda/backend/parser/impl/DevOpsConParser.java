package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Andreas
 *
 */
@Component
public class DevOpsConParser implements IEventParser {

	public DevOpsConParser() {
	}

	private ZonedDateTime parseEnd(Document doc) {
		Elements sessionInfo = doc.select(".gdlr-session-info");
		String date = sessionInfo.select(".session-time:has(.fa-calendar)").first().text();
		String period = sessionInfo.select(".session-time:has(.fa-clock-o)").first().text();
		return ZonedDateTime.parse(date + " " + period.split(" - ")[1] + " +02:00",
				DateTimeFormatter.ofPattern("d LLL uuuu HH:mm xxx").withLocale(Locale.GERMANY));
	}

	private ZonedDateTime parseBegin(Document doc) {
		Elements sessionInfo = doc.select(".gdlr-session-info");
		String date = sessionInfo.select(".session-time:has(.fa-calendar)").first().text();
		String period = sessionInfo.select(".session-time:has(.fa-clock-o)").first().text();
		return ZonedDateTime.parse(date + " " + period.split(" - ")[0] + " +02:00",
				DateTimeFormatter.ofPattern("d LLL uuuu HH:mm xxx").withLocale(Locale.GERMANY));
	}

	private String parseTitle(Document doc) {
		Elements titles = doc.select("h1.gdlr-session-title");
		return titles.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
	}

	@Override
	public String getBaseUri() {
		return "devopsconference.de";
	}

	@Override
	public List<CalendarEvent> parse(Document doc) {
		return Arrays.asList(new CalendarEvent(parseTitle(doc), parseBegin(doc), parseEnd(doc)));
	}
}
