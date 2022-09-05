package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parser(String fileToString, String fileFormat) throws IOException {
        ObjectMapper mapper;
        if (fileFormat.equals(".yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else {
            mapper = new ObjectMapper();
        }
        return mapper.readValue(fileToString, Map.class);
    }
}
