package com.hundsun.house.bean.vo;

import com.hundsun.house.bean.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserVo extends User {
    private String confirmPasswd;
    private String newPassword;
    private MultipartFile avatarFile;
    private String key;
    private String agencyName;
}
