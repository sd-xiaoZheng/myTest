package com.test.photo;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import com.luciad.imageio.webp.WebPReadParam;
import com.luciad.imageio.webp.WebPWriteParam;


/**
 * @author myDemo
 * @since 2025/6/25
 **/
public class Jpg2Webp {
    public static void main(String[] args) {
        String oldfile = "D:\\step\\file\\image\\2025\\photoType\\Temp\\4be7c9b479024628908aa9cb82614610_1750508397331.jpg";
        String newfile = "D:\\step\\file\\image\\2025\\photoType\\Temp\\123.webp";
        // 单张指定文件转换
        jpg2webp(oldfile, newfile);
//        webp2jpg(oldfile, newfile);
        // 批量将文件夹跟目录webp格式图片转成JPG格式
//        ListFileWebp2jpg("F:\\picture\\");
    }

    /**
     * 批量将文件夹跟目录webp格式图片转成JPG格式
     *
     * @param folderPath F:/picture/
     * @return
     */
    public static void ListFileWebp2jpg(String folderPath) {

        File file = new File(folderPath);
        File[] files = file.listFiles();

        if (files != null) {
            for (File file0 : files) {
                if (file0.isFile() && file0.getName().endsWith(".webp")) {
                    System.out.println(file0.getName());
                    String oldfile = file0.getAbsolutePath();
                    String newfile = file0.getAbsolutePath().replace(".webp", ".jpg");
                    webp2jpg(oldfile, newfile);

                }
            }
        }


    }

    /**
     * webp格式图片转成JPG格式
     *
     * @param oldfile c:/1.test.webp
     * @param newfile c:/1.test.jpg
     * @return
     */
    public static void webp2jpg(String oldfile, String newfile) {
        // 创建WebP ImageReader实例
        ImageReader reader = ImageIO.getImageReadersByMIMEType("image/webp").next();
        // 配置解码参数
        WebPReadParam readParam = new WebPReadParam();
        readParam.setBypassFiltering(true);
        // 在ImageReader设置读取的原文件
        try {
            reader.setInput(new FileImageInputStream(new File(oldfile)));
            // 解码图像
            BufferedImage image = reader.read(0, readParam);
            // 设置输入文件的格式和文件名
            ImageIO.write(image, "jpg", new File(newfile)); // 这里也可以使用其他图片格式，但是格式和文件名后缀要保持一致
            System.out.println("webp文件转成png格式成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JPG格式图片转成webp格式(也可以是其他格式图片)
     *
     * @param oldfile c:/1.test.jpg
     * @param newfile c:/1.test.webp
     * @return
     */
    public static void jpg2webp(String oldfile, String newfile) {

        try {
            // 获取原始文件的编码
            BufferedImage image = ImageIO.read(new File(oldfile));
            // 创建WebP ImageWriter实例
            ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
            // 配置编码参数
            WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
            // 设置压缩模式
            writeParam.setCompressionMode(WebPWriteParam.MODE_DEFAULT);
            // 配置ImageWriter输出
            writer.setOutput(new FileImageOutputStream(new File(newfile)));
            // 进行编码，重新生成新图片
            writer.write(null, new IIOImage(image, null, null), writeParam);
            System.out.println("jpg文件转成webp格式成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
