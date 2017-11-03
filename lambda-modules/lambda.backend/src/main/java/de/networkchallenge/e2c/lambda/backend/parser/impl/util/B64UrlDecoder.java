package de.networkchallenge.e2c.lambda.backend.parser.impl.util;

import java.util.Base64;
import java.util.Optional;

public class B64UrlDecoder {
    public static Optional<String> parse(String base64urlsafeString)
    {
        try {
            return Optional.of(new String(Base64.getUrlDecoder().decode(base64urlsafeString)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
