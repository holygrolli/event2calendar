package de.networkchallenge.e2c.lambda.backend.handler;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.model.ResponseEvent;
import de.networkchallenge.e2c.lambda.backend.model.ResponseObject;
import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import de.networkchallenge.e2c.lambda.backend.parser.impl.ParserRegistry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by GrollAnd on 25.05.2017.
 */
public class ResponseBuilder {
    private final ParserRegistry registry;
    private final URL url;

    public ResponseBuilder(ParserRegistry registry, URL url) {
        this.registry = Objects.requireNonNull(registry);
        this.url = Objects.requireNonNull(url);
    }

    public ResponseObject build()
    {
        final ResponseObject responseObject = new ResponseObject();
        Optional<IEventParser> parser = registry.getParserForUrl(url.getHost());
        if (parser.isPresent())
        {
            try {
                List<ResponseEvent> responseEvents = new ArrayList<>();
                Document docFromUrl = getDocFromUrl(url);
                List<CalendarEvent> events = parser.get().parse(docFromUrl);
                for(CalendarEvent event : events)
                {
                    responseEvents.add(new ResponseEvent().setEventTitle(event.getTitle())
                            .setEventBegin(event.getEventBeginGMT())
                            .setEventEnd(event.getEventEndGMT())
                            .setEventSource(url.toString())
                            .setEventLocation(event.getLocation()));
                }
                responseObject.setEvents(responseEvents);
                responseObject.setStatus(ResponseObject.Status.OK);
            } catch (IOException e) {
                responseObject.setStatus(ResponseObject.Status.ERROR_LOADING);
            }
        }
        else {
            System.err.println("URL not supported: " + url);
            responseObject.setStatus(ResponseObject.Status.URL_NOT_SUPPORTED);
        }
        return responseObject;
    }

    private Document getDocFromUrl(URL url) throws IOException {
        return Jsoup.parse(url, 3000);
    }
}
