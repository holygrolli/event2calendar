package de.networkchallenge.e2c.lambda.backend.parser.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 
 * @author Andreas
 *
 */
public class ParserRegistryTest {
	private ParserRegistry parserRegistry = ParserRegistry.getInstance();

	@Test
	public void testRegistryHasRegisteredParsers() {
		assertTrue(parserRegistry.getRegisteredCount() > 0);
		assertNotNull(parserRegistry.getParserForUrl("devopsconference.de").get());
		assertNotNull(parserRegistry.getParserForUrl("devopscon.io").get());
		assertNotNull(parserRegistry.getParserForUrl("jax.de").get());
		assertNotNull(parserRegistry.getParserForUrl("azuresaturday.de").get());
		assertEquals(parserRegistry.getParserForUrl("films2017.dok-leipzig.de"),parserRegistry.getParserForUrl("filmfinder.dok-leipzig.de"));
	}
}
