package de.networkchallenge.e2c.lambda.backend.model;

public class ResponseEvent {

    private String eventTitle;
    private String eventBegin;
    private String eventEnd;
    private String eventSource;
    private String eventLocation;

    public String getEventTitle() {
        return eventTitle;
    }

    public ResponseEvent setEventTitle(final String title) {
        this.eventTitle = title;
        return this;
    }

    public String getEventBegin() {
        return eventBegin;
    }

    public ResponseEvent setEventBegin(String eventBegin) {
        this.eventBegin = eventBegin;
        return this;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public ResponseEvent setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
        return this;
    }

    public String getEventSource() {
        return eventSource;
    }

    public ResponseEvent setEventSource(String eventSource) {
        this.eventSource = eventSource;
        return this;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public ResponseEvent setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
        return this;
    }
}
