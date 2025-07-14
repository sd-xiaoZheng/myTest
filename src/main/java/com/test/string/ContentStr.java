package com.test.string;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author myDemo
 * @since 2025/6/20
 **/
public class ContentStr {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> templateStr = new ArrayList<>();
        ArrayList<String> greeting = new ArrayList<>();
        ArrayList<String> recipientName = new ArrayList<>();
        ArrayList<String> messageSubject = new ArrayList<>();
        ArrayList<String> closingRemark = new ArrayList<>();
        ArrayList<String> emojiIcon = new ArrayList<>();
        // 添加问候语测试数据
        greeting.add("你好");
        greeting.add("早上好");
        greeting.add("下午好");
        greeting.add("晚上好");

        // 添加称呼测试数据
        recipientName.add("张三");
        recipientName.add("李四");
        recipientName.add("客户");
        recipientName.add("朋友");

        // 添加主题测试数据
        messageSubject.add("产品介绍");
        messageSubject.add("活动邀请");
        messageSubject.add("订单确认");
        messageSubject.add("售后跟进");

        // 添加结束语测试数据
        closingRemark.add("祝好");
        closingRemark.add("此致");
        closingRemark.add("顺祝商祺");
        closingRemark.add("敬上");

        // 添加表情测试数据
        emojiIcon.add("😊");
        emojiIcon.add("👍");
        emojiIcon.add("🎁");
        emojiIcon.add("🌟");

        // 将所有列表添加到总列表
        templateStr.add(greeting);
        templateStr.add(recipientName);
        templateStr.add(messageSubject);
        templateStr.add(closingRemark);
        templateStr.add(emojiIcon);

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            StringBuilder combinedMessage = new StringBuilder();

            // 遍历每个子列表，随机选择元素
            for (ArrayList<String> subList : templateStr) {
                int randomIndex = random.nextInt(subList.size());
                combinedMessage.append(subList.get(randomIndex)).append(" ");
            }

            // 输出生成的组合
            System.out.println("随机组合 " + (i + 1) + ": " + combinedMessage.toString().trim());
        }
    }
}
