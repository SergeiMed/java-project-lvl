package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static Map<String, Object> parser(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Map<String, Object> yml1 = mapper.readValue(file, Map.class);
        Map<String, Object> sortedMap = new TreeMap<>(yml1);
        return sortedMap;
    }
}
