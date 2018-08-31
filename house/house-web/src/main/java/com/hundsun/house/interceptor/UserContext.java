package com.hundsun.house.interceptor;

import com.hundsun.house.bean.User;

public class UserContext {
    private static final ThreadLocal<User> USER_HODLER = new ThreadLocal<>();

    public static void setUser(User user) {
        USER_HODLER.set(user);
    }

    public static void removue() {
        USER_HODLER.remove();
    }

    public static User getUser() {
        return USER_HODLER.get();
    }
}
