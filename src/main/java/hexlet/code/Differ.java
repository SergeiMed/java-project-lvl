package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "Version",
        description = "Compares two configuration files and shows a difference.")

public class Differ implements Callable<String> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File file;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File file1;
    @Option(names = { "-f", "--format" }, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Override
    public final String call() throws Exception {
        return generate(file, file1);
    }

    public static void main(String[] args) {
        new CommandLine(new Differ()).execute(args);
    }

    public static String generate(File file1, File file2) throws IOException {
        Map<String, Object> map1 = Parser.parser(file1);
        Map<String, Object> map2 = Parser.parser(file2);
        StringBuilder builder = new StringBuilder("{\n");
        for (Map.Entry<String, Object> map : map1.entrySet()) {
            if (!map2.containsKey(map.getKey())) {
                builder.append("- ").append(map.getKey()).append(": ").append(map.getValue()).append("\n");
            }
            if (map2.containsKey(map.getKey())) {
                if (!map.getValue().equals(map2.get(map.getKey()))) {
                    builder.append("- ").append(map.getKey()).append(": ")
                            .append(map.getValue()).append("\n");
                    builder.append("+ ").append(map.getKey()).append(": ")
                            .append(map2.get(map.getKey())).append("\n");
                } else if (map2.containsKey(map.getKey())
                        && map.getValue().equals(map2.get(map.getKey()))) {
                    builder.append("  ").append(map.getKey()).append(": ").append(map.getValue()).append("\n");
                }
            }
        }
        for (Map.Entry<String, Object> map3 : map2.entrySet()) {
            if (!map1.containsKey(map3.getKey())) {
                builder.append("+ ").append(map3.getKey()).append(": ").append(map3.getValue()).append("\n");
            }
        }
        builder.append("}");
        String result = builder.toString();
        System.out.println(result);
        return result;
    }
}
