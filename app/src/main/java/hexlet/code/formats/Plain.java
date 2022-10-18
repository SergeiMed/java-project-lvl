package hexlet.code.formats;

import hexlet.code.ValueInfo;

import java.util.Map;

public class Plain {

    private static String formatValueInString(Object value) {
        if (!(value instanceof String
                || value instanceof Boolean
                || value instanceof Integer
                || value == null)) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value == null) {
            return "null";
        }
        return value.toString();
    }

    public static String format(Map<String, ValueInfo<Object>> map) throws Exception {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ValueInfo<Object>> entry : map.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case "changed" -> builder.append("Property '")
                            .append(entry.getKey())
                            .append("' was updated. From ")
                            .append(formatValueInString(entry.getValue().getFirstValue()))
                            .append(" to ")
                            .append(formatValueInString(entry.getValue().getSecondValue()))
                            .append("\n");
                case "new" -> builder.append("Property '")
                        .append(entry.getKey())
                        .append("' was added with value: ")
                        .append(formatValueInString(entry.getValue().getSecondValue()))
                        .append("\n");
                case "deleted" -> builder.append("Property '")
                        .append(entry.getKey())
                        .append("' was removed\n");
                default -> {
                    if (!entry.getValue().getStatus().equals("unchanged")) {
                        throw new Exception("incorrect status");
                    }
                }
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
