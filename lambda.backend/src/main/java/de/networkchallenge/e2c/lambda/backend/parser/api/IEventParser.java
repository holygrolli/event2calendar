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
	 * Returns the base URIs, i.e. FQDN of the target site which the parser is
	 * built for. This is everything of the target URL without protocol and
	 * path. For example a parser for events of
	 * https://events.example.com/event/some_event should return
	 * "events.example.com"
	 * 
	 * @return {@link List} of {@link String} with the base URIs
	 */
	List<String> getBaseUris();

	/**
	 * Parses a site for an event, extracts the event and returns it as
	 * {@link CalendarEvent}.
	 * 
	 * @param doc
	 *            the {@link Document} of the event page
	 * @return
	 */
	List<CalendarEvent> parse(Document doc);

}
