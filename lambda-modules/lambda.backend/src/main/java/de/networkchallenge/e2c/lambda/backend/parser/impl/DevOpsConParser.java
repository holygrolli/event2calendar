package de.networkchallenge.e2c.lambda.backend.parser.impl;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Andreas
 */
public class DevOpsConParser extends AbstractSuSMediaParser {
    @Override
    ZoneId getZoneId() {
        return ZoneId.of("Europe/Berlin");
    }

    @Override
    public List<String> getBaseUris() {
        return Arrays.asList("devopsconference.de", "devopscon.io");
    }

    @Override
    protected String getLocation() {
        return "Mercure Hotel MOA, Berlin";
    }

}
