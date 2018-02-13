package de.networkchallenge.e2c.lambda.backend.handler;

import com.google.gson.Gson;
import de.networkchallenge.e2c.lambda.backend.model.ResponseObject;
import de.networkchallenge.e2c.lambda.backend.parser.impl.ParserRegistry;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.B64UrlDecoder;
import spark.Request;
import spark.Response;

import java.net.URL;

import static spark.Spark.get;

public class UrlController {


    public UrlController() {
        Gson gson = new Gson();
        get("/url/:url", UrlController::parse, gson::toJson);
        get("/parse", UrlController::parse, gson::toJson);
    }

    public static ResponseObject parse(Request request, Response response) {
        String url = request.params(":url");
        return getResponseObject(url);
    }

    private static ResponseObject getResponseObject(String url) {
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

    public static ResponseObject parseRequestMapping(Request request, Response response) {
        String url = request.queryParams("url");
        return getResponseObject(url);
    }
}
