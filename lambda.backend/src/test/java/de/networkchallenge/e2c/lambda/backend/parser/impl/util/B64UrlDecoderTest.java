package de.networkchallenge.e2c.lambda.backend.parser.impl.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class B64UrlDecoderTest {
    @Test
    public void parse() throws Exception {
        assertEquals("https://films2017.dok-leipzig.de/de/film/?ID=16412",B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8%2FSUQ9MTY0MTI%3D").get());
        assertEquals("https://films2017.dok-leipzig.de/de/film/?ID=16412",B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8/SUQ9MTY0MTI%3D").get());
        assertEquals("https://films2017.dok-leipzig.de/de/film/?ID=16412",B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8/SUQ9MTY0MTI=").get());
        assertEquals("https://films2017.dok-leipzig.de/en/film/?ID=16502&title=Airport",B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZW4vZmlsbS8%2FSUQ9MTY1MDImdGl0bGU9QWlycG9ydA%3D%3D").get());
    }

}