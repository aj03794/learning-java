package concurrent_variables_and_atomic_variables;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionProblems {
    public static void main(String[] args) {
//        var stringStringMap = new HashMap<String, String>();
        var stringStringMap = new ConcurrentHashMap<String, String>();

        stringStringMap.put("Maaike", "Java");
        stringStringMap.put("Remsey", "C#");

        for (var k: stringStringMap.keySet()) {
            System.out.println(k + " loves codes " + stringStringMap.get(k));
            stringStringMap.remove(k);
        }
    }
}
