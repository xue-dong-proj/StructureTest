package nuc.edu;

import nuc.edu.map.Map;
import nuc.edu.map.TreeMap;

/**
 * @author 薛东
 * @date 2021/4/3 18:49
 */
public class Main {
    public static void main(String[] args) {
        test01();
    }

    static void test01(){
        Map<String,Integer> map = new TreeMap<>();
        map.put("class",2);
        map.put("public",5);
        map.put("text",6);
        map.put("public",8);

        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(value);
                return false;
            }
        });
    }
}
