package com.hundsun.house.service;

public interface MailService {
    void registerNotify(String email);

    boolean enable(String key);

    void resetNotify(String email);

    String getResetEmail(String key);

    void invalidateRestKey(String key);
}
