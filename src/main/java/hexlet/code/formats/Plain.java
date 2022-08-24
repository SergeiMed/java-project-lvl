package hexlet.code.formats;

import hexlet.code.ValueInfo;

import java.util.Map;

public class Plain {

    public static String plain(Map<String, ValueInfo<Object>> map) throws Exception {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ValueInfo<Object>> entry : map.entrySet()) {
            if (!(entry.getValue().getFirstValue() instanceof String
                    || entry.getValue().getFirstValue() instanceof Boolean
                    || entry.getValue().getFirstValue() instanceof Integer
                    || entry.getValue().getFirstValue() == null)) {
                entry.getValue().setFirstValue("[complex value]");
            } else if (entry.getValue().getFirstValue() instanceof String) {
                entry.getValue().setFirstValue("'" + entry.getValue().getFirstValue() + "'");
            }
            if (!(entry.getValue().getSecondValue() instanceof String
                    || entry.getValue().getSecondValue() instanceof Boolean
                    || entry.getValue().getSecondValue() instanceof Integer
                    || entry.getValue().getSecondValue() == null)) {
                entry.getValue().setSecondValue("[complex value]");
            } else if (entry.getValue().getSecondValue() instanceof String) {
                entry.getValue().setSecondValue("'" + entry.getValue().getSecondValue() + "'");
            }
        }
        for (Map.Entry<String, ValueInfo<Object>> entry : map.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case "changed" -> builder.append("Property '")
                            .append(entry.getKey())
                            .append("' was updated. From ")
                            .append(entry.getValue().getFirstValue())
                            .append(" to ")
                            .append(entry.getValue().getSecondValue())
                            .append("\n");
                case "new" -> builder.append("Property '")
                        .append(entry.getKey())
                        .append("' was added with value: ")
                        .append(entry.getValue().getSecondValue())
                        .append("\n");
                case "delete" -> builder.append("Property '")
                        .append(entry.getKey())
                        .append("' was removed\n");
                default -> {
                    if (!entry.getValue().getStatus().equals("unchanged")) {
                        throw new Exception("incorrect status!!!");
                    }
                }
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
