package hexlet.code;

import picocli.CommandLine;

@CommandLine.Command(
		name="gendiff",
		description="Compares two configuration files and shows a difference.")

public class Picocli {
	@CommandLine.Option(names={"-V", "--version"}, description="Print version information and exit.")
	boolean version;

	@CommandLine.Option(names={"-h", "--help"}, description="Show this help message and exit.", help=true)
	boolean help;
}
