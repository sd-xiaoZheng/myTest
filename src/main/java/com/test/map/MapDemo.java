package com.test.map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapDemo {

    public static void main(String[] args) {
        try {
            combinePic("D:\\dev\\idea_project\\myDemo\\myDemo\\src\\main\\java\\com/test/map/qianxi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void combinePic(String folderPath) throws IOException {
        // 读取地图轮廓图
        BufferedImage outlineImg = ImageIO.read(new File("D:\\dev\\idea_project\\myDemo\\myDemo\\src\\main\\java\\com/test/map/china.png"));

        int width = outlineImg.getWidth();
        int height = outlineImg.getHeight();

        // 新建与主图一样大的白色底图
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = newImg.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        int unitSize = 60;
        int xIndex = width / unitSize;
        int yIndex = height / unitSize;

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg")
        );

        if (files == null || files.length == 0) {
            System.out.println("文件夹中没有符合条件的图片。");
            return;
        }

        int total = files.length;
        int x = 0, y = 0;

        for (int i = 0; i < xIndex * yIndex; i++) {
            System.out.printf("目前进度 %d/%d%n", i, xIndex * yIndex);
            try {
                BufferedImage src = ImageIO.read(files[i % total]);
                Image scaled = src.getScaledInstance(unitSize, unitSize, Image.SCALE_SMOOTH);
                g2d.drawImage(scaled, x * unitSize, y * unitSize, null);
            } catch (IOException e) {
                System.out.println("有1位朋友的头像读取失败，跳过该头像");
                continue;
            }

            x++;
            if (x == xIndex) {
                x = 0;
                y++;
            }
        }

        g2d.dispose();
        System.out.println("素材图合成完毕！");
        ImageIO.write(newImg, "jpg", new File("out.jpg"));

        // 加载地图轮廓图并叠加
        BufferedImage outImage = ImageIO.read(new File("out.jpg"));
        BufferedImage chinaMap = ImageIO.read(new File("D:\\dev\\idea_project\\myDemo\\myDemo\\src\\main\\java\\com/test/map/china.png"));

        Graphics2D g = outImage.createGraphics();
        g.drawImage(chinaMap, 0, 0, null);
        g.dispose();

        ImageIO.write(outImage, "png", new File("result.png"));
        System.out.println("地图轮廓添加完毕！");
    }
}
