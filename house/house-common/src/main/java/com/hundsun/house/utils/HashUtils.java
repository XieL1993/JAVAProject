package com.hundsun.house.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class HashUtils {

    private static final String SALT = "org.hundsun.303";

    public static String encryPassword(String password) {
        return Hashing.md5().hashString(password + SALT, Charset.forName("UTF-8")).toString();
    }
}
