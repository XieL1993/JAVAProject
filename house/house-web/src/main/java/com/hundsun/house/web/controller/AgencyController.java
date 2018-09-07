package com.hundsun.house.web.controller;

import com.hundsun.house.bean.page.PageData;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.HouseVo;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.constants.CommonConstants;
import com.hundsun.house.service.AgencyService;
import com.hundsun.house.service.HouseService;
import com.hundsun.house.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AgencyController {
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("/agency/agentList")
    public String agentList(Integer pageSize, Integer pageNum, ModelMap modelMap) {
        if (pageSize == null) {
            pageSize = 6;
        }
        PageData<UserVo> users = agencyService.getAllAgent(PageParams.build(pageSize, pageNum));
        List<HouseVo> hotHouse = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", hotHouse);
        modelMap.put("ps", users);
        return "/user/agent/agentList";
    }
    @RequestMapping("/agency/agentDetail")
    public String agentDetail(Long id,ModelMap modelMap){
        UserVo agentDeail = agencyService.getAgentDeail(id);
        List<HouseVo> hotHouse = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        HouseVo query = new HouseVo();
        query.setUserId(id);
        query.setBookmarked(false);
        PageData<HouseVo> bindHouse = houseService.queryHouse(query, new PageParams(3, 1));
        if (bindHouse != null) {
            modelMap.put("bindHouses", bindHouse.getList()) ;
        }
        modelMap.put("recomHouses", hotHouse);
        modelMap.put("agent", agentDeail);
        modelMap.put("agencyName", agentDeail.getAgencyName());
        return "/user/agent/agentDetail";
    }
}
