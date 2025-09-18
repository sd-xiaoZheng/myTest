package com.test.jiami;

import cn.hutool.crypto.digest.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String a = "(151) 861 6667";
        String b = "i love u so much";
        String c = "1758187682656";
        String input = a + b + c;


        // 获取MD5摘要算法实例
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算摘要
        byte[] digest = md.digest(input.getBytes());
        // 转换成16进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte bt : digest) {
            sb.append(String.format("%02x", bt & 0xff));
        }
        System.out.println(sb.toString());
    }
}
