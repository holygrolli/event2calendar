package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Andreas
 *
 */
public class ParserRegistry {

	private static ParserRegistry instance = null;
	private static Object mutex = new Object();

	private static ServiceLoader<IEventParser> parsers = null;
	private static final Map<String, IEventParser> urlMapping = new HashMap<String, IEventParser>();
	private static final AtomicInteger size = new AtomicInteger(0);

	private ParserRegistry(){}

	public static ParserRegistry getInstance() {
		if (instance == null)
		{
			synchronized (mutex){
				if (instance == null){
					instance = new ParserRegistry();
					init();
				}
			}
		}
		return instance;
	}

	private static void init() {
		parsers = ServiceLoader.load(IEventParser.class);
		size.set(0);
		parsers.forEach(parser -> {
			size.addAndGet(1);
			parser.getBaseUris() //
					.forEach(url -> urlMapping.put(url, parser));
		});
	}

	public Optional<IEventParser> getParserForUrl(String url) {
		return Optional.ofNullable(urlMapping.get(url));
	}

	public int getRegisteredCount() {
		return size.get();
	}
}
