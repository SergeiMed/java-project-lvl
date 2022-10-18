package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {

    @Test
    public void testJsonDifferStylish() throws Exception {
        String expect = Files.readString(Path.of("./src/test/resources/fixtures/testJsonDifferStylish.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file1.json",
                "./src/test/resources/fixtures/file2.json", "stylish");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testJsonDifferDefault() throws Exception {
        String expect = Files.readString(Path.of("./src/test/resources/fixtures/testJsonDifferStylish.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file1.json",
                "./src/test/resources/fixtures/file2.json");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testJsonDifferPlain() throws Exception {
        String expect = Files.readString(Path.of("./src/test/resources/fixtures/testJsonDifferPlain.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file1.json",
                "./src/test/resources/fixtures/file2.json", "plain");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testJsonDifferJson() throws Exception {
        String jsonString = Files.readString(Path.of("./src/test/resources/fixtures/testJsonDifferJson.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file1.json",
                "./src/test/resources/fixtures/file2.json", "json");
        Assertions.assertEquals(jsonString, actual);
    }

    @Test
    public void testYamlDifferStylish() throws Exception {
        String expect = Files.readString(Path.of("./src/test/resources/fixtures/testYamlDifferStylish.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file1.yaml",
                "./src/test/resources/fixtures/file2.yaml", "stylish");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testYamlDifferDefault() throws Exception {
        String expect = Files.readString(Path.of("./src/test/resources/fixtures/testYamlDifferStylish.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file1.yaml",
                "./src/test/resources/fixtures/file2.yaml");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testYamlDifferPlain() throws Exception {
        String expect = Files.readString(Path.of("./src/test/resources/fixtures/testYamlDifferPlain.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file1.yaml",
                "./src/test/resources/fixtures/file2.yaml", "plain");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testYamlDifferJson() throws Exception {
        String jsonString = Files.readString(Path.of("./src/test/resources/fixtures/testYamlDifferJson.txt"));
        String actual = Differ.generate("./src/test/resources/fixtures/file1.yaml",
                "./src/test/resources/fixtures/file2.yaml", "json");
        Assertions.assertEquals(jsonString, actual);
    }
}
