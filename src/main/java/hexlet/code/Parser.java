package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    private static ObjectMapper mapper;

    public static Map<String, Object> parser(String fileToString, String fileFormat) throws IOException {
        switch (fileFormat) {
            case "yml", "yaml" -> {
                mapper = new ObjectMapper(new YAMLFactory());
            }
            case "json" -> {
                mapper = new ObjectMapper();
            }
            default -> throw new RuntimeException("Unknown format");
        }
        return mapper.readValue(fileToString, Map.class);
    }

    public static Map<String, Object> parseYaml(String fileToString) throws JsonProcessingException {
        mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(fileToString, Map.class);
    }
}
