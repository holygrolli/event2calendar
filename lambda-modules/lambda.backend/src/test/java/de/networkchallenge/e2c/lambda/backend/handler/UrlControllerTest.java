package de.networkchallenge.e2c.lambda.backend.handler;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.junit.Assert.assertEquals;

public class UrlControllerTest {

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
}
