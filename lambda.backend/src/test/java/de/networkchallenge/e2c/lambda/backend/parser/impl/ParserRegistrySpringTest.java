package de.networkchallenge.e2c.lambda.backend.parser.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 
 * @author Andreas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ParserRegistry.class, AzureSaturdayParser.class, DevOpsConParser.class})
public class ParserRegistrySpringTest {
	@Autowired
	private ParserRegistry parserRegistry;

	@Test
	public void testRegistryHasRegisteredParsers() {
		assertTrue(parserRegistry.getRegisteredCount() > 0);
		assertNotNull(parserRegistry.getParserForUrl("devopsconference.de"));
		assertEquals(parserRegistry.getParserForUrl("films2017.dok-leipzig.de"),parserRegistry.getParserForUrl("filmfinder.dok-leipzig.de"));
	}
}
