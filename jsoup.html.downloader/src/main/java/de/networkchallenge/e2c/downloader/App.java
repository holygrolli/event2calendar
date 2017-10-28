package de.networkchallenge.e2c.downloader;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {

	private static final String OPTION_SSL = "sslTrustAll";
	private static final String OPTION_HELP = "h";

	public static void main(String[] args) throws ParseException, IOException {
		Options options = new Options();
		options.addOption(OPTION_SSL, false, "disables all SSL checks to connect to all invalid certs");
		options.addOption(OPTION_HELP, "help", false, "shows this help text");
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);

		if (cmd.hasOption(OPTION_HELP)) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("jsoup.html.downloader", options);
		} else {
			new Downloader(cmd.getArgList().get(0), cmd.hasOption(OPTION_SSL)).save();
		}
	}

}
