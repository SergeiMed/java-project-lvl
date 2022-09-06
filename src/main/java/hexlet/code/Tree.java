package hexlet.code;

import java.util.Map;
import java.util.TreeMap;

public class Tree {

    public static Map<String, ValueInfo<Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
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
