package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Tree {

    public static Map<String, ValueInfo<Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, ValueInfo<Object>> differMap = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>();
        keySet.addAll(map1.keySet());
        keySet.addAll(map2.keySet());
        for (String key : keySet) {
            if (!map2.containsKey(key)) {
                differMap.put(key, new ValueInfo<>(map1.get(key), null, "deleted"));
            } else if (!map1.containsKey(key)) {
                differMap.put(key, new ValueInfo<>(null, map2.get(key), "new"));
            } else if (map1.get(key) == null && map2.get(key) == null) {
                differMap.put(key, new ValueInfo<>(null, null, "unchanged"));
            } else if (map1.get(key) == null || map2.get(key) == null) {
                differMap.put(key, new ValueInfo<>(map1.get(key), map2.get(key), "changed"));
            } else if (map1.get(key).equals(map2.get(key))) {
                differMap.put(key, new ValueInfo<>(map1.get(key), map2.get(key), "unchanged"));
            } else {
                differMap.put(key, new ValueInfo<>(map1.get(key), map2.get(key), "changed"));
            }
        }
        return differMap;
    }
}
