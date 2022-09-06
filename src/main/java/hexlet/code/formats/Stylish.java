package hexlet.code.formats;

import hexlet.code.ValueInfo;

import java.util.Map;

public class Stylish {

    public static String format(Map<String, ValueInfo<Object>> map) throws Exception {
        StringBuilder builder = new StringBuilder("{\n");
        for (Map.Entry<String, ValueInfo<Object>> entry : map.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case "changed" -> {
                    builder.append("  - ")
                            .append(entry.getKey())
                            .append(": ")
                            .append(entry.getValue().getFirstValue())
                            .append("\n");
                    builder.append("  + ")
                            .append(entry.getKey())
                            .append(": ")
                            .append(entry.getValue().getSecondValue())
                            .append("\n");
                }
                case "unchanged" -> builder.append("    ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue().getFirstValue())
                        .append("\n");
                case "new" -> builder.append("  + ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue().getSecondValue())
                        .append("\n");
                case "delete" -> builder.append("  - ")
                        .append(entry.getKey())
                        .append(": ")
                        .append(entry.getValue().getFirstValue())
                        .append("\n");
                default -> throw new Exception("incorrect status");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
