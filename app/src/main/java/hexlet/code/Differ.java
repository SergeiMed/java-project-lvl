package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {
        return Formatter.getFormattedString(genDiff(file1, file2), format);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    private static String getDataFormat(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".")).substring(1);
    }

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static Map<String, Object> getData(String filePath) throws IOException {
        String fileToString = Files.readString(getFullPath(filePath));
        return Parser.parser(fileToString, getDataFormat(filePath));
    }

    private static Map<String, ValueInfo<Object>> genDiff(String file1, String file2) throws IOException {
        Map<String, Object> map1 = getData(file1);
        Map<String, Object> map2 = getData(file2);
        return Tree.build(map1, map2);
    }
}
