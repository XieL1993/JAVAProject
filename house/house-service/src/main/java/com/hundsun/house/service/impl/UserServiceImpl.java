package com.hundsun.house.service.impl;

import com.google.common.collect.Lists;
import com.hundsun.house.bean.User;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.mapper.UserMapper;
import com.hundsun.house.service.FileService;
import com.hundsun.house.service.MailService;
import com.hundsun.house.service.UserService;
import com.hundsun.house.utils.BeanHelper;
import com.hundsun.house.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private MailService mailService;

    @Value("${file.prefix}")
    private String imgPrefix;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAccount(UserVo user) {
        user.setPasswd(HashUtils.encryPassword(user.getPasswd()));
        List<String> imgList = fileService.getImgPaths(Lists.newArrayList(user.getAvatarFile()));
        if (!imgList.isEmpty()) {
            user.setAvatar(imgList.get(0));
        }
        BeanHelper.setDefaultProp(user, UserVo.class);
        BeanHelper.onInsert(user);
        user.setEnable(0);
        userMapper.insert(user);
        mailService.registerNotify(user.getEmail());
        return true;
    }

    @Override
    public boolean enable(String key) {
        return mailService.enable(key);
    }

    public User auth(String username, String password) {
        User user = new User();
        user.setEmail(username);
        user.setPasswd(HashUtils.encryPassword(password));
        user.setEnable(1);
        List<User> list = getUserByQuery(user);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<User> getUserByQuery(User user) {
        List<User> users = userMapper.selectUsersByQuery(user);
        users.forEach(u -> u.setAvatar(imgPrefix + u.getAvatar()));
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User query = new User();
        query.setId(id);
        List<User> users = getUserByQuery(query);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void updateUser(UserVo userVo, String email) {
        userVo.setEmail(email);
        BeanHelper.onUpdate(userVo);
        userMapper.update(userVo);
    }

    @Override
    public void resetNotify(String username) {
        mailService.resetNotify(username);
    }

    @Override
    public String getResetEmail(String key) {
        return mailService.getResetEmail(key);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User reset(String key, String password) {
        String resetEmail = getResetEmail(key);
        UserVo userVo = new UserVo();
        userVo.setEmail(resetEmail);
        userVo.setPasswd(HashUtils.encryPassword(password));
        userMapper.update(userVo);
        mailService.invalidateRestKey(key);
        User user = new User();
        user.setEmail(resetEmail);
        List<User> users = getUserByQuery(user);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
