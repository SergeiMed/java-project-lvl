package hexlet.code;

import hexlet.code.formats.Json;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {

    public static String getFormattedString(Map<String, ValueInfo<Object>> map, String format) throws Exception {
        switch (format) {
            case "stylish" -> {
                return Stylish.format(map);
            }
            case "plain" -> {
                return Plain.format(map);
            }
            case "json" -> {
                return Json.format(map);
            }
            default -> throw new IOException("unknown format!!!!");
        }
    }
}
