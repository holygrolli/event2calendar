package de.networkchallenge.e2c.lambda.backend.parser.impl;

import de.networkchallenge.e2c.lambda.backend.parser.api.IEventParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Andreas
 *
 */
@Service
public class ParserRegistry {

	@Autowired
	private List<IEventParser> parsers;

	private final static Map<String, IEventParser> urlMapping = new HashMap<String, IEventParser>();

	@PostConstruct
	public void init() {
		parsers.forEach(parser -> parser.getBaseUris() //
				.forEach(url -> urlMapping.put(url, parser)));
	}

	public Optional<IEventParser> getParserForUrl(String url) {
		return Optional.ofNullable(urlMapping.get(url));
	}

	public int getRegisteredCount() {
		return parsers.size();
	}
}
