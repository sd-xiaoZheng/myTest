package com.test.photo;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.GpsDirectory;

import java.io.File;
import java.io.IOException;

/**
 * @author myDemo
 * @since 2025/5/29
 **/
public class App2 {
    public static void main(String[] args) throws ImageProcessingException, IOException {
        File imageFile = new File("D:\\step\\file\\image\\2025\\photoType\\Temp\\6a54ca095a0e493b9b4b4838db5aedd1_1750315684466.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(imageFile);

        // 拍摄时间
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if (tag.getTagName().equals("Date/Time Original")) {
                    System.out.println("拍摄时间: " + tag.getDescription());
                }
                if (tag.getTagName().equals("Model")) {
                    System.out.println("拍摄设备: " + tag.getDescription());
                }
                if (tag.getTagName().equals("GPS Altitude")) {
                    System.out.println("拍摄海拔: " + tag.getDescription());
                }
            }
        }

        // GPS 信息
        GpsDirectory gpsDir = metadata.getFirstDirectoryOfType(GpsDirectory.class);
        if (gpsDir != null) {
            GeoLocation geoLocation = gpsDir.getGeoLocation();
            if (geoLocation != null) {
                System.out.println("拍摄地点: " + geoLocation.getLatitude() + ", " + geoLocation.getLongitude());
            } else {
                System.out.println("没有 GPS 位置信息。");
            }
        } else {
            System.out.println("未找到 GPS 信息。");
        }
    }
}
