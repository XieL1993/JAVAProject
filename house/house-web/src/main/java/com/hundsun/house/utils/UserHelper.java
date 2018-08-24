package com.hundsun.house.utils;

import com.google.common.base.Objects;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.result.ResultMsg;
import org.apache.commons.lang3.StringUtils;

public class UserHelper {
    public static ResultMsg validate(UserVo accout) {
        if (StringUtils.isBlank(accout.getEmail())) {
            return ResultMsg.errorMsg("Email不能为空");
        }
        if (StringUtils.isBlank(accout.getPhone())) {
            return ResultMsg.errorMsg("手机号不能为空");
        }
        if (StringUtils.isBlank(accout.getConfirmPasswd()) || StringUtils.isBlank(accout.getPasswd())
                || !accout.getPasswd().equals(accout.getConfirmPasswd())) {
            return ResultMsg.errorMsg("两次输入密码不一致");
        }
        if (accout.getPasswd().length() < 6) {
            return ResultMsg.errorMsg("密码需要大于6位");
        }
        return ResultMsg.successMsg("");
    }

    public static ResultMsg validateResetPassword(String key, String password, String confirmPassword) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return ResultMsg.errorMsg("参数有误");
        }
        if (!Objects.equal(password, confirmPassword)) {
            return ResultMsg.errorMsg("密码必须与确认密码一致");
        }
        return ResultMsg.successMsg("");
    }
}
