package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.SuSMediaDateParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.TimeParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DokLeipzigParser implements IEventParser {
    private final static TimeParser TP = new TimeParser("dd.MM.yyyy HH:mm", Locale.GERMANY);
    private final static TimeParser TP_EN = new TimeParser("dd/MM/yyyy HH:mm", Locale.ENGLISH);

    @Override
    public List<String> getBaseUris() {
        return Arrays.asList("films2018.dok-leipzig.de", "films2019.dok-leipzig.de","filmfinder.dok-leipzig.de");
    }

    @Override
    public List<CalendarEvent> parse(Document doc) {
        String title = parseTitle(doc);
        Elements eventItems = findEventItems(doc);
        ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();
        eventItems.stream().forEach((element) -> {calendarEvents.add(parseEventItem(doc, element, title));
        });
        return calendarEvents;
    }

    private CalendarEvent parseEventItem(Document doc, Element event, String title) {
        return new CalendarEvent(title, parseBegin(doc, event), parseEnd(doc, event), parseLocation(event));
    }

    private String parseLocation(Element element) {
        String location = "";
        if (element.select("table").size()>0)
        {
            Elements table_label = element.select("div label");
            location = table_label.get(0).text();
        }
        else {
            location = element.select("label").get(0).text();
        }

        return location;
    }

    private Elements findEventItems(Document doc) {
        Elements elements = doc.select("div.event-item");
        if (elements.size()==0)
        {
            elements = doc.select("div.content-detail-screening");
        }
        return elements;
    }

    private ZonedDateTime parseEnd(Document doc, Element element) {
        return parseBegin(doc, element).plusMinutes(90);
    }

    private ZonedDateTime parseBegin(Document doc, Element event) {
        String date, time;
        if (event.select("table").size()>0)
        {
            Elements table_label = event.select("table label");
            date = table_label.get(0).text();
            time = table_label.get(1).text();
        }
        else {
            date = event.select("label").get(1).text();
            time = event.select("label").get(2).text();
        }
        return parseLocaleDateTime(doc, date + " " + time, ZoneId.of("Europe/Berlin"));
    }

    private String parseTitle(Document doc) {
        Elements select = doc.select("h1#mainContent");
        return select.stream().findFirst().orElseThrow(() -> new IllegalStateException("no title found")).text();
    }

    private ZonedDateTime parseLocaleDateTime(Document doc, String date, ZoneId timezone) {
        if (doc.select("html").first().attr("lang").startsWith("en"))
            return TP_EN.parse(date, timezone);
        else
            return TP.parse(date, timezone);
    }
}
