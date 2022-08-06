package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
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
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> scoreByName = mapper.readValue(file, Map.class);
        Map<String, Object> scoreByName1 = mapper.readValue(file1, Map.class);
        Map<String, Object> sortedMap = new TreeMap<>(scoreByName);
        Map<String, Object> sortedMap1 = new TreeMap<>(scoreByName1);
        StringBuilder builder = new StringBuilder("{\n");
        for (Map.Entry<String, Object> map : sortedMap.entrySet()) {
            if (!sortedMap1.containsKey(map.getKey())) {
                builder.append("- ").append(map.getKey()).append(": ").append(map.getValue()).append("\n");
            }
            if (sortedMap1.containsKey(map.getKey())) {
                if (!map.getValue().equals(sortedMap1.get(map.getKey()))) {
                    builder.append("- ").append(map.getKey()).append(": ")
                            .append(map.getValue()).append("\n");
                    builder.append("+ ").append(map.getKey()).append(": ")
                            .append(sortedMap1.get(map.getKey())).append("\n");
                } else if (sortedMap1.containsKey(map.getKey())
                        && map.getValue().equals(sortedMap1.get(map.getKey()))) {
                    builder.append("  ").append(map.getKey()).append(": ").append(map.getValue()).append("\n");
                }
            }
        }
        for (Map.Entry<String, Object> map1 : sortedMap1.entrySet()) {
            if (!sortedMap.containsKey(map1.getKey())) {
                builder.append("+ ").append(map1.getKey()).append(": ").append(map1.getValue()).append("\n");
            }
        }
        builder.append("}\n");
        String result = builder.toString();
        System.out.println(result);
        return result;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
