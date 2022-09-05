package hexlet.code;

import hexlet.code.formats.Json;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;
import hexlet.code.formats.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {
        switch (format) {
            case "stylish" -> {
                System.out.println(Stylish.stylish(genDiff(file1, file2)));
                return Stylish.stylish(genDiff(file1, file2));
            }
            case "plain" -> {
                System.out.println(Plain.plain(genDiff(file1, file2)));
                return Plain.plain(genDiff(file1, file2));
            }
            case "json" -> {
                System.out.println(Json.json(genDiff(file1, file2)));
                return Json.json(genDiff(file1, file2));
            }
            default -> throw new IOException("unknown format!!!!");
        }
    }

    public static String generate(String file1, String file2) throws Exception {
        System.out.println(Stylish.stylish(genDiff(file1, file2)));
        return Stylish.stylish(genDiff(file1, file2));
    }

    public static Map<String, Object> getData(String filePath) throws IOException {
        String fileFormat;
        String fileToString;
        try {
            fileToString = Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new IOException("incorrect filePath");
        }
        if (filePath.endsWith(".json")) {
            fileFormat = ".json";
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            fileFormat = ".yml";
        } else {
            throw new IOException("Incorrect file format");
        }
        return Parser.parser(fileToString, fileFormat);
    }

    static Map<String, ValueInfo<Object>> genDiff(String file1, String file2) throws IOException {
        Map<String, Object> map1 = getData(file1);
        Map<String, Object> map2 = getData(file2);
        return Tree.build(map1, map2);
    }
}
