package de.networkchallenge.e2c.lambda.backend.parser.impl;

import java.util.Collections;
import java.util.List;

/**
 * @author Andreas
 */
public class DevOpsConParser extends AbstractSuSMediaParser {

    DevOpsConParser() {
    }

    @Override
    public List<String> getBaseUris() {
        return Collections.singletonList("devopsconference.de");
    }

    @Override
    protected String getLocation() {
        return "Mercure Hotel MOA, Berlin";
    }

}
