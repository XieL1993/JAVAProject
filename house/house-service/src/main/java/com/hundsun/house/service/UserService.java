package com.hundsun.house.service;


import com.hundsun.house.bean.User;
import com.hundsun.house.bean.vo.UserVo;

import java.util.List;

public interface UserService {

    boolean addAccount(UserVo user);

    boolean enable(String key);

    User auth(String username, String password);

    List<User> getUserByQuery(User user);

    void updateUser(UserVo userVo, String email);

    void resetNotify(String username);

    String getResetEmail(String key);

    User reset(String key, String password);
}
