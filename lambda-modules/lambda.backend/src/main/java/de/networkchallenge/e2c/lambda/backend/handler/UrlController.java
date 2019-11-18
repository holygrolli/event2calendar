package de.networkchallenge.e2c.lambda.backend.handler;

import de.networkchallenge.e2c.lambda.backend.model.ResponseObject;
import de.networkchallenge.e2c.lambda.backend.parser.impl.ParserRegistry;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.B64UrlDecoder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URL;

@Path("/url")
public class UrlController {

    @GET
    @Path("{url}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseObject getResponseObject(@PathParam("url") String url) {
        ResponseObject responseObject = new ResponseObject();
        try {
            URL eventUrl = new URL(B64UrlDecoder.parse(url).orElseThrow(() -> new IllegalArgumentException(url)));
            responseObject = new ResponseBuilder(ParserRegistry.getInstance(), eventUrl).build();
        } catch (Exception e) {
            System.err.println("invalid URL: " + url + " [" + e.getClass().getSimpleName() + "(" + e.getMessage() + ")]");
            responseObject.setStatus(ResponseObject.Status.URL_INVALID);
        }
        return responseObject;
    }

}
