package com.test.string;

import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;

import java.util.TreeMap;

/**
 * @author MyDemo
 * @since 2025/6/4
 **/
public class strContains {
    public static void main(String[] args) {
        // 字典数据准备
        TreeMap<String, String> map = new TreeMap<>();
        map.put("小象超市", "小象");
        map.put("完美平台", "完美");
        map.put("腾讯", "腾讯");
        map.put("智联", "智联");
        map.put("美团", "美团");
        map.put("京东外卖", "京东");
        map.put("招商银行", "招商");
        map.put("中国邮政", "邮政");
        map.put("淘宝闪购", "淘宝");
        map.put("夸克", "夸克");
        map.put("得物App", "得物");
        map.put("宝闪", "宝闪");

        // 字典树构建
        AhoCorasickDoubleArrayTrie<String> act = new AhoCorasickDoubleArrayTrie<>();
        act.build(map);
        // 关键词提取
        act.parseText("【招商银行】验证码051124，您正在用一网通登录，如非本人操作得物，请联系95555。请勿在任何短信得物App或邮件链接的页面中输入验证码！", (begin, end, value) -> {
            System.out.printf("[%d:%d]=%s\n", begin, end, value);
        });
    }
}
