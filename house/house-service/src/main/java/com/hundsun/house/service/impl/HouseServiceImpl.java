package com.hundsun.house.service.impl;

import com.google.common.collect.Lists;
import com.hundsun.house.bean.HouseUser;
import com.hundsun.house.bean.UserMsg;
import com.hundsun.house.bean.page.PageData;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.HouseVo;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.mapper.HouseMapper;
import com.hundsun.house.service.AgencyService;
import com.hundsun.house.service.HouseService;
import com.hundsun.house.service.MailService;
import com.hundsun.house.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements HouseService {
    @Value("${file.prefix}")
    private String imgPrefix;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private MailService mailService;

    @Override
    public List<HouseVo> queryAndSetImg(HouseVo query, PageParams pageParams) {
        List<HouseVo> houses = houseMapper.selectPageHouses(query, pageParams);
        houses.forEach(h -> {
            h.setFirstImg(imgPrefix + h.getFirstImg());
            h.setImageList(h.getImageList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
            h.setFloorPlanList(h.getFloorPlanList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
        });
        return houses;
    }

    @Override
    public PageData<HouseVo> queryHouse(HouseVo query, PageParams pageParams) {
        List<HouseVo> houses = Lists.newArrayList();
        houses = queryAndSetImg(query, pageParams);
        Long count = houseMapper.selectPageCount(query);
        return PageData.buildPage(houses, count, pageParams.getPageSize(), pageParams.getPageNum());
    }

    @Override
    public HouseVo queryOneHouse(Long id) {
        HouseVo houseVo = new HouseVo();
        houseVo.setId(id);
        List<HouseVo> list = queryAndSetImg(houseVo, PageParams.build(1, 1));
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public HouseUser getHouseUser(Long houseId) {
        return houseMapper.selectSaleHouseUser(houseId);
    }

    @Override
    public void addUserMsg(UserMsg userMsg) {
        userMsg.setCreateTime(new Date());
        houseMapper.insertUserMsg(userMsg);
        UserVo deail = agencyService.getAgentDeail(userMsg.getAgentId());
        mailService.sendMail("来自用户" + userMsg.getUserName() + "的留言", userMsg.getMsg(), deail.getEmail());
    }
}
