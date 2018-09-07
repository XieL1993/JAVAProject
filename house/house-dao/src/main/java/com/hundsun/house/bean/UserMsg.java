package com.hundsun.house.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserMsg {
    private Long id;
    private String msg;
    private Date createTime;
    private Long agentId;
    private Long houseId;
    private String userName;
    private String email;
}
