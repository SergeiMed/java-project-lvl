package hexlet.code.formats;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ValueInfo;

import java.io.IOException;
import java.util.Map;

public class Json {

    public static String format(Map<String, ValueInfo<Object>> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }
}
