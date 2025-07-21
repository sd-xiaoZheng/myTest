package com.test.string;

import java.util.HashMap;

/**
 * @author myTest
 * @since 2025/7/20
 **/
public class printMap {
    public static void main(String[] args) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("a", "b");
        stringStringHashMap.put("c", "d");
        stringStringHashMap.put("e", "f");
        System.out.println(stringStringHashMap);
    }
}
