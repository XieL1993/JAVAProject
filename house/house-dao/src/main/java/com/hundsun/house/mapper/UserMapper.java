package com.hundsun.house.mapper;

import com.hundsun.house.bean.User;
import com.hundsun.house.bean.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectUsersByQuery(User user);

    int delete(String email);

    int insert(UserVo user);

    int update(UserVo user);

}
