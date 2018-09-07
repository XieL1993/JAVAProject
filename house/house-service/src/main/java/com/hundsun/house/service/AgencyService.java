package com.hundsun.house.service;

import com.hundsun.house.bean.Agency;
import com.hundsun.house.bean.page.PageData;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.UserVo;

import java.util.List;

public interface AgencyService {
    UserVo getAgentDeail(Long userId);

    List<Agency> getAllAgency();

    PageData<UserVo> getAllAgent(PageParams pageParams);

}
