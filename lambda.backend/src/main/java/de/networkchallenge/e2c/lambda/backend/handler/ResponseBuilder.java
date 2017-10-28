package de.networkchallenge.e2c.lambda.backend.handler;

import de.networkchallenge.e2c.lambda.backend.dto.CalendarEvent;
import de.networkchallenge.e2c.lambda.backend.model.Response;
import de.networkchallenge.e2c.lambda.backend.model.ResponseEvent;
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

    public Response build()
    {
        final Response response = new Response();
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
                            .setEventSource(url.toString()));
                }
                response.setEvents(responseEvents);
                response.setStatus(Response.Status.OK);
            } catch (IOException e) {
                response.setStatus(Response.Status.ERROR_LOADING);
            }
        }
        else {
            response.setStatus(Response.Status.URL_NOT_SUPPORTED);
        }
        return response;
    }

    private Document getDocFromUrl(URL url) throws IOException {
        return Jsoup.parse(url, 3000);
    }
}
