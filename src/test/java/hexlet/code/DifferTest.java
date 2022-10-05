package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {

    @Test
    public void testGenerate() throws Exception {
        String expect = Files.readString(Path.of("./src/test/resources/fixtures/testGenerate.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file.json",
                "./src/test/resources/fixtures/file1.json", "stylish");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testDifferPlain() throws Exception {
        String expect = Files.readString(Path.of("./src/test/resources/fixtures/testDifferPlain.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file.json",
                "./src/test/resources/fixtures/file1.json", "plain");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testDifferJson() throws Exception {
        String jsonString = Files.readString(Path.of("./src/test/resources/fixtures/testDifferJson.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file.json",
                "./src/test/resources/fixtures/file1.json", "json");
        Assertions.assertEquals(jsonString, actual);
    }
}
