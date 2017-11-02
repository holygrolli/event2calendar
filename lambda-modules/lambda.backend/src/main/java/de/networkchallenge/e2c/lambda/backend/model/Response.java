package de.networkchallenge.e2c.lambda.backend.model;

import java.util.List;

public class Response {

    public void setEvents(List<ResponseEvent> events) {
        this.events = events;
    }

    public List<ResponseEvent> getEvents() {
        return events;
    }

    public enum Status {
        OK, ERROR, URL_NOT_SUPPORTED, ERROR_LOADING, URL_INVALID
    }

    private List<ResponseEvent> events;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public Response setStatus(final Status status) {
        this.status = status;
        return this;
    }

}