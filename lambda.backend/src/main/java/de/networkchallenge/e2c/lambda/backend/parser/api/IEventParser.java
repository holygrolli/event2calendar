package de.networkchallenge.e2c.lambda.backend.parser.api;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author Andreas
 *
 */
public interface IEventParser {
	/**
	 * Returns the base URI, i.e. FQDN of the target site which the parser is
	 * built for. This is everything of the target URL without protocol and
	 * path. For example a parser for events of
	 * https://events.example.com/event/some_event should return
	 * "events.example.com"
	 * 
	 * @return {@link String} of the base URI
	 */
	public String getBaseUri();

	/**
	 * Parses a site for an event, extracts the event and returns it as
	 * {@link CalendarEvent}.
	 * 
	 * @param doc
	 *            the {@link Document} of the event page
	 * @return
	 */
	public List<CalendarEvent> parse(Document doc);

}
