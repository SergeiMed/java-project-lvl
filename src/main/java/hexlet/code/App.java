package hexlet.code;

import picocli.CommandLine;

public class App {

    public static void main(String[] args) {
        final Picocli picocli = CommandLine.populateCommand(new Picocli(), args);
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
