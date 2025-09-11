package com.test.string;

public class split {
    public static void main(String[] args) {
        String a = "asdasd";
        String[] split = a.split(",");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
