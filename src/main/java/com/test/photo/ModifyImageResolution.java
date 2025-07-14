package com.test.photo;


import cn.hutool.core.util.IdUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.bytedeco.javacpp.Loader;

import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author myDemo
 * @since 2025/6/25
 **/
public class ModifyImageResolution {
    public static void main(String[] args) throws Exception {
        modifyResolution("D:\\step\\file\\image\\2025\\photoType\\Temp\\4be7c9b479024628908aa9cb82614610_1750508397331.jpg", "D:\\step\\file\\image\\2025\\photoType\\Temp", 1280, 720);
    }

    /**
     * 修改图片分辨率
     *
     * @param imagePath 原图片地址
     * @param outputDir 输出目录
     * @param width     宽度
     * @param height    高度
     * @return 图片地址
     * @throws Exception 异常
     */
    public static String modifyResolution(
            String imagePath, String outputDir, Integer width, Integer height) throws Exception {
        List<String> paths = Splitter.on(".").splitToList(imagePath);
        String ext = paths.get(paths.size() - 1);
        if (!Arrays.asList("jpg", "png").contains(ext)) {
            throw new Exception("format error");
        }
        String resultPath =
                Joiner.on(File.separator).join(Arrays.asList(outputDir, IdUtil.simpleUUID() + "." + ext));
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        ProcessBuilder builder =
                new ProcessBuilder(
                        ffmpeg,
                        "-i",
                        imagePath,
                        "-vf",
                        MessageFormat.format("scale={0}:{1}", String.valueOf(width), String.valueOf(height)),
                        resultPath);
        builder.inheritIO().start().waitFor();
        return resultPath;
    }
}
