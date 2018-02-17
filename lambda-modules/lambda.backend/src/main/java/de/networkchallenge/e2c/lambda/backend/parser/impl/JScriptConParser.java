package de.networkchallenge.e2c.lambda.backend.parser.impl;

import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

/**
 * Parser for javascript-conference.com
 */
public class JScriptConParser extends AbstractSuSMediaParser {

    @Override
    public List<String> getBaseUris() {
        return Collections.singletonList("javascript-conference.com");
    }

    @Override
    ZoneId getZoneId() {
        return ZoneId.of("Europe/London");
    }

    @Override
    protected String getLocation() {
        return "Business Design Centre, London";
    }

}
