package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    private static ObjectMapper mapper;

    public static Map<String, Object> parse(String fileToString, String fileFormat) throws IOException {
        switch (fileFormat) {
            case "yml", "yaml" -> {
                return parseYaml(fileToString);
            }
            case "json" -> {
                return parseJson(fileToString);
            }
            default -> throw new RuntimeException("Unknown format!");
        }
    }

    public static Map<String, Object> parseYaml(String fileToString) throws IOException {
        mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(fileToString, Map.class);
    }

    public static Map<String, Object> parseJson(String fileToString) throws IOException {
        mapper = new ObjectMapper();
        return mapper.readValue(fileToString, Map.class);
    }
}
