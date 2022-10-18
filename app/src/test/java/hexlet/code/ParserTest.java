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
    public void parserJsonTest() throws IOException {
        String json = Files.readString(Path.of("./src/test/resources/fixtures/parserJsonTest.json"));
        Map<String, Object> expect = new HashMap<>();
        expect.put("host", "hexlet.io");
        expect.put("timeout", testNumber);
        expect.put("proxy", "123.234.53.22");
        expect.put("follow", false);
        Assertions.assertEquals(expect, Parser.parse(json, "json"));
    }

    @Test
    public void parserYamlTest() throws IOException {
        String yml = Files.readString(Path.of("./src/test/resources/fixtures/file1.yaml"));
        Map<String, Object> expect = new HashMap<>();
        expect.put("animal", "dog");
        expect.put("nickname", "Muhtar");
        expect.put("breed", "German shepherd");
        expect.put("color", "black and red");
        Assertions.assertEquals(expect, Parser.parse(yml, "yaml"));
    }
}
