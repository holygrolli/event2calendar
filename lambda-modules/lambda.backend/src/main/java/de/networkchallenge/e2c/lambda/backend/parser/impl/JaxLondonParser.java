package de.networkchallenge.e2c.lambda.backend.parser.impl;

import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

public class JaxLondonParser extends AbstractSuSMediaParser {
    @Override
    public List<String> getBaseUris() {
        return Collections.singletonList("finance.jaxlondon.com");
    }

    @Override
    ZoneId getZoneId() {
        return ZoneId.of("Europe/London");
    }

    @Override
    protected String getLocation() {
        return "Park Plaza Victoria, London";
    }
}
