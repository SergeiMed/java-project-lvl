package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "Version",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File file;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File file1;
    @Option(names = { "-f", "--format" }, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Override
    public final String call() throws Exception {
        Differ differ = new Differ(file, file1);
        return differ.generate();
    }

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
        //int exitCode = new CommandLine(new App()).execute(args);
    }
}
