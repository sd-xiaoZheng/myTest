package com.test.GetToken;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;

import static cn.hutool.crypto.SecureUtil.sha1;

public class GetToken {
    public static void main(String[] args) {
        // 获取纽约时间（UTC-4），秒级时间戳
        long timeStamp = ZonedDateTime.now(ZoneId.of("America/New_York")).toEpochSecond();

        String rawSign = "hulan-api" + "S78U38FXT7731" + timeStamp;
        String sign = sha1(rawSign);

        String rawToken = "hulan-api" + "," + timeStamp + "," + sign;

        System.out.println(Base64.getEncoder().encodeToString(rawToken.getBytes(StandardCharsets.UTF_8)));
    }

    private static String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // 转十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA1 算法不可用", e);
        }
    }

}
