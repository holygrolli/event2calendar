package de.networkchallenge.e2c.lambda.backend.parser.impl;

import java.util.Collections;
import java.util.List;

/**
 * Parser for jax.de
 */
public class JaxParser extends AbstractSuSMediaParser {

    @Override
    public List<String> getBaseUris() {
        return Collections.singletonList("jax.de");
    }

    @Override
    protected String getLocation() {
        return "Rheingoldhalle, Mainz";
    }

}
