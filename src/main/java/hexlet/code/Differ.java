package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {
        return Formatter.getFormattedString(genDiff(file1, file2), format);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
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
