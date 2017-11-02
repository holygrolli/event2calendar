package de.networkchallenge.e2c.lambda.backend.handler;

import de.networkchallenge.e2c.lambda.backend.model.Response;
import de.networkchallenge.e2c.lambda.backend.parser.impl.ParserRegistry;
import de.networkchallenge.e2c.lambda.backend.parser.impl.util.B64UrlDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.URL;

@RestController
@EnableWebMvc
@CrossOrigin
public class UrlController {

	@Autowired
	private ParserRegistry parserRegistry;

	@RequestMapping("/url/{url}")
	@ResponseBody
	public Response parse(@PathVariable String url)
	{
		Response response = new Response();
		try {
			URL eventUrl = new URL(B64UrlDecoder.parse(url).orElseThrow(() -> new IllegalArgumentException(url)));
			response = new ResponseBuilder(parserRegistry, eventUrl).build();
		} catch (Exception e) {
			System.err.println("invalid URL: " + url);
			response.setStatus(Response.Status.URL_INVALID);
		}
		return response;
	}

	@RequestMapping("/parse")
	@ResponseBody
	public Response parseRequestMapping(@RequestParam("url") String url)
	{
		Response response = new Response();
		try {
			URL eventUrl = new URL(B64UrlDecoder.parse(url).orElseThrow(() -> new IllegalArgumentException(url)));
			response = new ResponseBuilder(parserRegistry, eventUrl).build();
		} catch (Exception e) {
			System.err.println("invalid URL: " + url);
			response.setStatus(Response.Status.URL_INVALID);
		}
		return response;
	}
}
