package hexlet.code;

import hexlet.code.formats.Json;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(File file1, File file2, String format) throws Exception {
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

    static Map<String, ValueInfo<Object>> genDiff(File file1, File file2) throws IOException {
        String fileToString = Files.readString(Paths.get(String.valueOf(file1)));
        String fileToString1 = Files.readString(Paths.get(String.valueOf(file2)));
        Map<String, Object> map1 = Parser.parser(fileToString);
        Map<String, Object> map2 = Parser.parser(fileToString1);
        Map<String, ValueInfo<Object>> differMap = new TreeMap<>();
        for (Map.Entry<String, Object> map : map1.entrySet()) {
            if (map.getValue() == null) {
                map.setValue("null");
            }
            if (!map2.containsKey(map.getKey())) {
                differMap.put(map.getKey(), new ValueInfo<>(map.getValue(), null, "delete"));
            }
            if (map2.containsKey(map.getKey())) {
                if (!map.getValue().equals(map2.get(map.getKey()))) {
                    if (map.getValue().equals("null")) {
                        map.setValue(null);
                    }
                    differMap.put(map.getKey(), new ValueInfo<>(map.getValue(), map2.get(map.getKey()), "changed"));
                } else if (map2.containsKey(map.getKey())
                    && map.getValue().equals(map2.get(map.getKey()))) {
                    if (map.getValue().equals("null")) {
                        map.setValue(null);
                    }
                    differMap.put(map.getKey(), new ValueInfo<>(map.getValue(), map.getValue(), "unchanged"));
                }
            }
        }
        for (Map.Entry<String, Object> map3 : map2.entrySet()) {
            if (map3.getValue() == null) {
                map3.setValue("null");
            }
            if (!map1.containsKey(map3.getKey())) {
                differMap.put(map3.getKey(), new ValueInfo<>(null, map3.getValue(), "new"));
            }
        }
        return differMap;
    }
}
