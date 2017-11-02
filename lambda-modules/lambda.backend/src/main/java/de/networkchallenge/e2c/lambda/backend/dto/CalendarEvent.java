package de.networkchallenge.e2c.lambda.backend.dto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * 
 * Defining a calendar event.
 * 
 * @author Andreas
 *
 */
public class CalendarEvent {
	private String title;
	private ZonedDateTime eventBegin;
	private ZonedDateTime eventEnd;
	private String location;

	public CalendarEvent(String title, ZonedDateTime eventBegin, ZonedDateTime eventEnd, String location) {
		this.title = Objects.requireNonNull(title, "title must not be null");
		this.eventBegin = Objects.requireNonNull(eventBegin, "eventBegin must not be null");
		this.eventEnd = Objects.requireNonNull(eventEnd, "eventEnd must not be null");
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ZonedDateTime getEventBegin() {
		return eventBegin;
	}
	public String getEventBeginGMT() {
		return eventBegin.withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'", Locale.GERMANY));
	}

	public void setEventBegin(ZonedDateTime eventBegin) {
		this.eventBegin = eventBegin;
	}

	public ZonedDateTime getEventEnd() {
		return eventEnd;
	}
	public String getEventEndGMT() {
		return eventEnd.withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'", Locale.GERMANY));
	}

	public void setEventEnd(ZonedDateTime eventEnd) {
		this.eventEnd = eventEnd;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
