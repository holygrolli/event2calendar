package de.networkchallenge.e2c.lambda.backend.parser.impl.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Optional;

public class B64UrlDecoder {
    public static Optional<String> parse(String base64urlEncodedString)
    {
        try {
            return Optional.of(new String(Base64.getDecoder().decode(URLDecoder.decode(base64urlEncodedString, "UTF-8"))));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
