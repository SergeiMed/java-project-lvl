package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ParserTest {

    private final int testNumber = 50;

    @Test
    public void parserTest() throws IOException {
        String json = Files.readString(Path.of("./src/test/resources/fixtures/parserTest.json"));
        Map<String, Object> expect = new HashMap<>();
        expect.put("host", "hexlet.io");
        expect.put("timeout", testNumber);
        expect.put("proxy", "123.234.53.22");
        expect.put("follow", false);
        Assertions.assertEquals(expect, Parser.parser(json, "json"));
    }
}
