package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DifferTest {

    private static File file1;
    private static File file2;

    @BeforeEach
    public final void setupTest() throws IOException {
        file1 = new File("./src/test/resources/file.json");
        FileWriter fileWriter = new FileWriter(file1);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("""
                {
                  "setting1": "Some value",
                  "setting2": 200,
                  "setting3": true,
                  "key1": "value1",
                  "numbers1": [1, 2, 3, 4],
                  "numbers2": [2, 3, 4, 5],
                  "id": 45,
                  "default": null,
                  "checked": false,
                  "numbers3": [3, 4, 5],
                  "chars1": ["a", "b", "c"],
                  "chars2": ["d", "e", "f"]
                }""");
        printWriter.close();
        file2 = new File("./src/test/resources/file1.json");
        FileWriter fileWriter1 = new FileWriter(file2);
        PrintWriter printWriter1 = new PrintWriter(fileWriter1);
        printWriter1.print("""
                {
                  "setting1": "Another value",
                  "setting2": 300,
                  "setting3": "none",
                  "key2": "value2",
                  "numbers1": [1, 2, 3, 4],
                  "numbers2": [22, 33, 44, 55],
                  "id": null,
                  "default": ["value1", "value2"],
                  "checked": true,
                  "numbers4": [4, 5, 6],
                  "chars1": ["a", "b", "c"],
                  "chars2": false,
                  "obj1": {
                    "nestedKey": "value",
                    "isNested": true
                  }
                }""");
        printWriter1.close();
    }

    @Test
    public void testGenerate() throws Exception {
        String expect = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String actual = Differ.generate("./src/test/resources/file.json", "./src/test/resources/file1.json", "stylish");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testDifferPlain() throws Exception {
        String expect = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        String actual = Differ.generate("./src/test/resources/file.json", "./src/test/resources/file1.json", "plain");
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testDifferJson() throws Exception {
        String jsonString = """
                {
                  "chars1" : {
                    "firstValue" : [ "a", "b", "c" ],
                    "secondValue" : [ "a", "b", "c" ],
                    "status" : "unchanged"
                  },
                  "chars2" : {
                    "firstValue" : [ "d", "e", "f" ],
                    "secondValue" : false,
                    "status" : "changed"
                  },
                  "checked" : {
                    "firstValue" : false,
                    "secondValue" : true,
                    "status" : "changed"
                  },
                  "default" : {
                    "firstValue" : null,
                    "secondValue" : [ "value1", "value2" ],
                    "status" : "changed"
                  },
                  "id" : {
                    "firstValue" : 45,
                    "secondValue" : null,
                    "status" : "changed"
                  },
                  "key1" : {
                    "firstValue" : "value1",
                    "secondValue" : null,
                    "status" : "delete"
                  },
                  "key2" : {
                    "firstValue" : null,
                    "secondValue" : "value2",
                    "status" : "new"
                  },
                  "numbers1" : {
                    "firstValue" : [ 1, 2, 3, 4 ],
                    "secondValue" : [ 1, 2, 3, 4 ],
                    "status" : "unchanged"
                  },
                  "numbers2" : {
                    "firstValue" : [ 2, 3, 4, 5 ],
                    "secondValue" : [ 22, 33, 44, 55 ],
                    "status" : "changed"
                  },
                  "numbers3" : {
                    "firstValue" : [ 3, 4, 5 ],
                    "secondValue" : null,
                    "status" : "delete"
                  },
                  "numbers4" : {
                    "firstValue" : null,
                    "secondValue" : [ 4, 5, 6 ],
                    "status" : "new"
                  },
                  "obj1" : {
                    "firstValue" : null,
                    "secondValue" : {
                      "nestedKey" : "value",
                      "isNested" : true
                    },
                    "status" : "new"
                  },
                  "setting1" : {
                    "firstValue" : "Some value",
                    "secondValue" : "Another value",
                    "status" : "changed"
                  },
                  "setting2" : {
                    "firstValue" : 200,
                    "secondValue" : 300,
                    "status" : "changed"
                  },
                  "setting3" : {
                    "firstValue" : true,
                    "secondValue" : "none",
                    "status" : "changed"
                  }
                }""";
        String actual = Differ.generate("./src/test/resources/file.json", "./src/test/resources/file1.json", "json");
        Assertions.assertEquals(jsonString, actual);
    }
}
