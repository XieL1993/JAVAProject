package com.hundsun.house.service;

public interface MailService {
    void registerNotify(String email);

    boolean enable(String key);

    void sendMail(String title, String content, String email);

    void resetNotify(String email);

    String getResetEmail(String key);

    void invalidateRestKey(String key);
}
