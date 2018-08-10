package com.hundsun.video.utils;

import com.hundsun.video.properties.AttachProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UploadUtils {
    @Autowired
    private AttachProperties properties;

    private String[] getUUIDName(String realName) {
        int index = realName.lastIndexOf(".");
        String suffix = index == -1 ? "" : realName.substring(index);
        String[] arr = new String[2];
        arr[0] = UUIDUtils.getUUID();
        arr[1] = suffix;
        return arr;
    }

    private String getTodayPath() {
        String result = "";
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            result += formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String[] getDir(String name, Integer userId) {
        String[] uuids = getUUIDName(name);
        String uuidName = uuids[0];
        String suffix = uuids[1];
        int i = uuidName.hashCode();
        String hex = Integer.toHexString(i);
        int length = hex.length();
        for (int j = 0; j < 8 - length; j++) {
            hex = "0" + hex;
        }
        String basePath = properties.getUpload() + "/user" + userId + "/" + getTodayPath() + "/" + hex.charAt(0) + "/" + hex.charAt(1) + "/" + hex.charAt(2) + "/" + hex.charAt(3) + "/" + hex.charAt(4) + "/" + hex.charAt(5) + "/" + hex.charAt(6) + "/" + hex.charAt(7);
        File file = new File(properties.getRoot()+basePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String[] arr = new String[3];
        arr[0] = basePath + "/" + uuidName + suffix;
        arr[1] = basePath + "/" + uuidName + "_merge" + suffix;
        arr[2] = basePath + "/" + uuidName + "_cover" + ".jpg";
        return arr;
    }
}
