package de.networkchallenge.e2c.lambda.backend.parser.impl.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class B64UrlDecoderTest {
    @Test
    public void parse() throws Exception {
        assertFalse(B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8%2FSUQ9MTY0MTI%3D").isPresent());
        assertFalse(B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8/SUQ9MTY0MTI%3D").isPresent());
        assertEquals("https://films2017.dok-leipzig.de/de/film/?ID=16412",B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8_SUQ9MTY0MTI=").get());
        assertEquals("https://films2017.dok-leipzig.de/en/film/?ID=16502&title=Airport",B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZW4vZmlsbS8_SUQ9MTY1MDImdGl0bGU9QWlycG9ydA==").get());
        assertEquals("https://films2017.dok-leipzig.de/de/film/?ID=18371&title=Betrug",B64UrlDecoder.parse("aHR0cHM6Ly9maWxtczIwMTcuZG9rLWxlaXB6aWcuZGUvZGUvZmlsbS8_SUQ9MTgzNzEmdGl0bGU9QmV0cnVn").get());
    }

}