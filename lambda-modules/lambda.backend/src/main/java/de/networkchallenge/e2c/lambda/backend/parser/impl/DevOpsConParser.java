package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.SuSMediaDateParser;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andreas
 *
 */
public class DevOpsConParser implements IEventParser {

	public DevOpsConParser() {
	}

	private ZonedDateTime parseEnd(Document doc) {
		Elements sessionInfo = doc.select("span.ws-label");
		String date = sessionInfo.first().ownText().split(",")[1].trim();
		String period = sessionInfo.select("span").get(1).text();
		return SuSMediaDateParser.parseLocaleDateTime(doc,date + " " + period.split(" - ")[1] + " +02:00");
	}

	private ZonedDateTime parseBegin(Document doc) {
		Elements sessionInfo = doc.select("span.ws-label");
		String date = sessionInfo.first().ownText().split(",")[1].trim();
		String period = sessionInfo.select("span").get(1).text();
		return SuSMediaDateParser.parseLocaleDateTime(doc,date + " " + period.split(" - ")[0] + " +02:00");
	}

	private String parseTitle(Document doc) {
		Elements titles = doc.select("h2.gdlr-page-title");
		return titles.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
	}

	@Override
	public List<String> getBaseUris() {
		return Arrays.asList("devopsconference.de");
	}

	@Override
	public List<CalendarEvent> parse(Document doc) {
		return Arrays.asList(new CalendarEvent(parseTitle(doc), parseBegin(doc), parseEnd(doc), ""));
	}
}
