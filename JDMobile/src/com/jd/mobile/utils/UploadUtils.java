package com.jd.mobile.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtils {
    public static String getUUIDName(String realName){
        int index = realName.lastIndexOf(".");
            return index==-1?UUIDUtils.getUUID():UUIDUtils.getUUID()+realName.substring(index);
    }
    public static String getTodayPath(){
        String result = "/";
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            result+= formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getDir(String name){
        int i = name.hashCode();
        String hex = Integer.toHexString(i);
        int length=hex.length();
        for(int j=0;j<8-length;j++){
            hex="0"+hex;
        }
        return getTodayPath()+"/"+hex.charAt(0)+"/"+hex.charAt(1)+"/"+hex.charAt(2)+"/"+hex.charAt(3)+"/"+hex.charAt(4)+"/"+hex.charAt(5)+"/"+hex.charAt(6)+"/"+hex.charAt(7);
    }
}
