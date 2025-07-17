package com.test.file.txt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author myTest
 * @since 2025/7/17
 * 它会读取一个 TXT 文件，删除其中所有空行
 **/
public class txt1 {
    public static void main(String[] args) throws IOException {
        readUserName("C:\\Users\\33312\\Desktop\\11.txt");
//        removeEmptyLines("C:\\Users\\33312\\Desktop\\data_sess_tn (4).txt","C:\\Users\\33312\\Desktop\\11.txt");
    }
    public static void removeEmptyLines(String inputFilePath, String outputFilePath) throws IOException {
            // 读取所有行
            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));

        for (String line : lines) {
            JSONObject jsonObject = JSON.parseObject(line);
            System.out.println(jsonObject.get("userName"));
        }
            // 过滤掉空行（去掉前后空白字符后为空的行）
            List<String> cleanedLines = lines.stream()
                    .filter(line -> !line.trim().isEmpty())
                    .collect(Collectors.toList());

            // 写回文件（可以是原文件或新文件）
            Files.write(Paths.get(outputFilePath), cleanedLines);

            System.out.println("空行已成功移除！");
    }

    public static void readUserName(String inputFilePath) throws IOException {
        // 读取所有行
        List<String> lines = Files.readAllLines(Paths.get(inputFilePath));

        for (String line : lines) {
            JSONObject jsonObject = JSON.parseObject(line);
            System.out.println(jsonObject.get("userName"));
        }
    }

}
