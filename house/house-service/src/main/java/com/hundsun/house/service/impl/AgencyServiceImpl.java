package com.hundsun.house.service.impl;

import com.hundsun.house.bean.Agency;
import com.hundsun.house.bean.page.PageData;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.mapper.AgencyMapper;
import com.hundsun.house.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    private AgencyMapper agencyMapper;

    @Value("${file.prefix}")
    private String imgPrefix;

    @Override
    public UserVo getAgentDeail(Long userId) {
        UserVo user = new UserVo();
        user.setId(userId);
        user.setType(2);
        List<UserVo> users = agencyMapper.selectAgentUser(user, PageParams.build(1, 1));
        users.forEach(i -> i.setAvatar(imgPrefix + i.getAvatar()));
        if (!users.isEmpty()) {
            UserVo u = users.get(0);
            Agency agency = new Agency();
            agency.setId(u.getAgencyId().intValue());
            List<Agency> agencies = agencyMapper.select(agency);
            if (!agencies.isEmpty()) {
                u.setAgencyName(agencies.get(0).getName());
            }
            return u;
        }
        return null;
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyMapper.select(new Agency());
    }

    @Override
    public PageData<UserVo> getAllAgent(PageParams pageParams) {
        List<UserVo> users = agencyMapper.selectAgentUser(new UserVo(), pageParams);
        users.forEach(i -> i.setAvatar(imgPrefix + i.getAvatar()));
        Long count = agencyMapper.selectAgentCount(new UserVo());
        return PageData.buildPage(users, count, pageParams.getPageSize(), pageParams.getPageNum());
    }
}
