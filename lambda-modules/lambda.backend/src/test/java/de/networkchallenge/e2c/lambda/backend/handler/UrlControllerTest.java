package de.networkchallenge.e2c.lambda.backend.handler;

import de.networkchallenge.e2c.lambda.backend.model.ResponseObject;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class UrlControllerTest {

	private static Logger LOG = Logger.getLogger(UrlControllerTest.class.getName());

	@Test
	public void convertHtmlToBase64() throws UnsupportedEncodingException {
		assertEquals(
				"aHR0cHM6Ly9kZXZvcHNjb25mZXJlbmNlLmRlL3Nlc3Npb24vd2ViLWhhY2tpbmctcGVudGVzdGluZy1hbmQtYXR0YWNraW5nLXdlYi1hcHBzLw==",
				URLDecoder.decode(
						"aHR0cHM6Ly9kZXZvcHNjb25mZXJlbmNlLmRlL3Nlc3Npb24vd2ViLWhhY2tpbmctcGVudGVzdGluZy1hbmQtYXR0YWNraW5nLXdlYi1hcHBzLw==",
						"UTF-8"));
		assertEquals(
				"aHR0cHM6Ly9kZXZvcHNjb25mZXJlbmNlLmRlL3Nlc3Npb24vd2ViLWhhY2tpbmctcGVudGVzdGluZy1hbmQtYXR0YWNraW5nLXdlYi1hcHBzLw==",
				URLDecoder.decode(
						"aHR0cHM6Ly9kZXZvcHNjb25mZXJlbmNlLmRlL3Nlc3Npb24vd2ViLWhhY2tpbmctcGVudGVzdGluZy1hbmQtYXR0YWNraW5nLXdlYi1hcHBzLw%3D%3D",
						"UTF-8"));
		assertEquals(
				"aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8/SUQ9MTY0MTI=",
				URLDecoder.decode(
						"aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8%2FSUQ9MTY0MTI%3D",
						"UTF-8"));
	}

	@Test
	public void urlResourceInvalidUrl()
	{
		String url = new String(Base64.getUrlEncoder().encode("Hello world!".getBytes()));
		RestAssured.when().get("/url/" + url).then()
				.contentType(MediaType.APPLICATION_JSON)
				.body("status", Matchers.equalTo(ResponseObject.Status.URL_INVALID.toString()));
	}
	@Test
	public void urlResourceOk1() throws UnsupportedEncodingException {
		String url = new String(Base64.getUrlEncoder().encode("https://films2019.dok-leipzig.de/de/film/?ID=16412".getBytes()));
		LOG.info("using URL: " + url);
		RestAssured.when().get("/url/" + url).then()
				.contentType(MediaType.APPLICATION_JSON)
				.body("status", Matchers.equalTo(ResponseObject.Status.OK.toString()));
	}
	@Test
	public void urlResourceOk2() throws UnsupportedEncodingException {
		String url = new String(Base64.getUrlEncoder().encode("https://films2019.dok-leipzig.de/de/film/?ID=18371&title=Betrug".getBytes()));
		LOG.info("using URL: " + url);
		RestAssured.when().get("/url/" + url).then()
				.contentType(MediaType.APPLICATION_JSON)
				.body("status", Matchers.equalTo(ResponseObject.Status.OK.toString()));
	}
}
