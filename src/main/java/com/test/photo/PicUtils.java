package com.test.photo;

import com.luciad.imageio.webp.WebPWriteParam;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PicUtils {
    public static void main(String[] args) throws IOException {
//        resizeImage("D:\\step\\file\\image\\2025\\photoType\\Temp\\4be7c9b479024628908aa9cb82614610_1750508397331.jpg",
//                "D:\\step\\file\\image\\2025\\photoType\\Temp\\max\\42222221.jpg");
        convertAndGenerateWebpThumbnail("D:\\step\\file\\image\\2025\\photoType\\Temp\\4be7c9b479024628908aa9cb82614610_1750508397331.jpg",
                "D:\\step\\file\\image\\2025\\photoType\\Temp\\max\\42222221.webp", 0.5f, 0.2f);
    }

    public static void resizeImage(String srcPath, String destPath) throws IOException {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        BufferedImage srcImage = ImageIO.read(srcFile);
        int srcWidth = srcImage.getWidth();
        int srcHeight = srcImage.getHeight();
        // 选择较小的缩放比例
        double scale = 0.1960785f;
        // 计算缩放后的宽度和高度
        int newWidth = (int) (srcWidth * scale);
        int newHeight = (int) (srcHeight * scale);
        BufferedImage destImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = destImage.createGraphics();
        // 设置渲染参数
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(srcImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        ImageIO.write(destImage, "jpg", destFile);
    }

    public static void convertAndGenerateWebpThumbnail(String imagePath, String thumbOutputPath, float scale, float quality) {
        try {
            File file = new File(imagePath);
            if (!file.exists()) {
                System.out.println("文件不存在: " + file.getAbsolutePath());
                return;
            }
            // 1. 读取原图
            BufferedImage originalImage = ImageIO.read(file);

            // 2. 先“压缩为 WebP”的概念其实只是：你控制后续写 WebP 的压缩质量参数。
            // WebP是输出格式，压缩不是处理完再转图的意思，而是写输出的时候控制的。
            // 所以这一步你不需要提前生成 WebP 文件，只需要使用原始图就行。

            // 3. 缩略图处理
            int srcWidth = originalImage.getWidth();
            int srcHeight = originalImage.getHeight();

            int newWidth = (int) (srcWidth * scale);
            int newHeight = (int) (srcHeight * scale);

            BufferedImage thumbnail = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = thumbnail.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            // 4. 将缩略图输出为 WebP 格式
            ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
            WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
            writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSY_COMPRESSION]);
            writeParam.setCompressionQuality(quality); // 设置压缩质量

            File outputFile = new File(thumbOutputPath);
            File parentDir = outputFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean created = parentDir.mkdirs();
                if (!created) {
                    throw new IOException("无法创建目录: " + parentDir.getAbsolutePath());
                }
            }
            writer.setOutput(new FileImageOutputStream(outputFile));
            writer.write(null, new IIOImage(thumbnail, null, null), writeParam);

            writer.dispose();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
 