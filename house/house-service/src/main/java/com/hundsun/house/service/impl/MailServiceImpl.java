package com.hundsun.house.service.impl;

import com.google.common.base.Objects;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.hundsun.house.bean.User;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.mapper.UserMapper;
import com.hundsun.house.service.MailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    private static String domainName;
    @Value("${server.port}")
    private String port;
    @Autowired
    private UserMapper userMapper;
    private final Cache<String, String> registerCache =
            CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES).removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    String email = notification.getValue();
                    User user = new User();
                    user.setEmail(email);
                    List<User> users = userMapper.selectUsersByQuery(user);
                    if (!users.isEmpty() && Objects.equal(users.get(0).getEnable(), 0)) {
                        userMapper.delete(email);
                    }
                }
            }).build();
    private final Cache<String, String> resetCache = CacheBuilder.newBuilder().maximumSize(100)
            .expireAfterAccess(15, TimeUnit.MINUTES).build();

    static {
        try {
            domainName = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Async
    @Override
    public void sendMail(String title, String content, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(title);
        message.setTo(email);
        message.setText(content);
        mailSender.send(message);
    }

    @Async
    @Override
    public void registerNotify(String email) {
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        registerCache.put(randomKey, email);
        String content = "http://" + domainName + ":" + port + "/accounts/verify?key=" + randomKey;
        sendMail("房产平台激活邮件", content, email);
    }

    @Override
    public boolean enable(String key) {
        String email = registerCache.getIfPresent(key);
        if (StringUtils.isBlank(email)) {
            return false;
        }
        UserVo userVo = new UserVo();
        userVo.setEmail(email);
        userVo.setEnable(1);
        userMapper.update(userVo);
        registerCache.invalidate(key);
        return true;
    }

    @Async
    @Override
    public void resetNotify(String email) {
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        resetCache.put(randomKey, email);
        String content = "http://" + domainName + ":" + port + "/accounts/reset?key=" + randomKey;
        sendMail("房产平台密码重置邮件", content, email);
    }

    @Override
    public String getResetEmail(String key) {
        return resetCache.getIfPresent(key);
    }

    @Override
    public void invalidateRestKey(String key) {
        resetCache.invalidate(key);
    }
}
