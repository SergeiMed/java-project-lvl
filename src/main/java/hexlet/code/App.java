package hexlet.code;

import picocli.CommandLine;
import java.io.File;

@CommandLine.Command(
        name="gendiff",
        description="Compares two configuration files and shows a difference.")

public class App {

    @CommandLine.Option(names={"-V", "--version"}, description="Print version information and exit.")
    boolean version;

    @CommandLine.Option(names={"-h", "--help"}, description="Show this help message and exit.", help=true)
    boolean help;

    @CommandLine.Option(names = { "-f", "--format" }, paramLabel = "format", defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    File file;

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file", defaultValue = "")
    private File filepath1;

    @CommandLine.Parameters(paramLabel = "filepath2", description = "path to second file", defaultValue = "")
    private File filepath2;

    public static void main(String[] args) {
        final App picocli = CommandLine.populateCommand(new App(), args);
        if (picocli.help)
        {
            CommandLine.usage(picocli, System.out, CommandLine.Help.Ansi.AUTO);
            return;
        }
        else if (picocli.version)
        {
            System.out.println("Version");
            return;
        }
        System.out.println("Hello World!");
    }
}
