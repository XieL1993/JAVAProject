package com.hundsun.house.web.controller;

import com.hundsun.house.bean.House;
import com.hundsun.house.bean.HouseUser;
import com.hundsun.house.bean.User;
import com.hundsun.house.bean.UserMsg;
import com.hundsun.house.bean.page.PageData;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.CommentVo;
import com.hundsun.house.bean.vo.HouseVo;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.constants.CommonConstants;
import com.hundsun.house.interceptor.UserContext;
import com.hundsun.house.result.ResultMsg;
import com.hundsun.house.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private CityService cityService;

    @RequestMapping("/house/list")
    public String houseList(Integer pageSize, Integer pageNum, HouseVo query, ModelMap modelMap) {
        PageData<HouseVo> data = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
        List<HouseVo> hotHouses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("ps", data);
        modelMap.put("recomHouses", hotHouses);
        modelMap.put("vo", query);
        return "house/listing";
    }

    @RequestMapping("house/detail")
    public String houseDetail(Long id, ModelMap modelMap) {
        recommendService.increase(id);

        HouseVo house = houseService.queryOneHouse(id);
        modelMap.put("house", house);

        List<CommentVo> comments = commentService.getHouseComments(id, 8);
        modelMap.put("commentList", comments);

        HouseUser houseUser = houseService.getHouseUser(id);
        if (houseUser.getUserId() != null && !houseUser.getUserId().equals(0)) {
            UserVo agentDeail = agencyService.getAgentDeail(houseUser.getUserId());
            modelMap.put("agent", agentDeail);
        }
        List<HouseVo> hotHouses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", hotHouses);

        return "/house/detail";
    }

    @RequestMapping("house/leaveMsg")
    public String houseMsg(UserMsg userMsg) {
        houseService.addUserMsg(userMsg);
        return "redirect:/house/detail?id=" + userMsg.getHouseId() + "&" + ResultMsg.successMsg("留言成功").asUrlParams();
    }

    @RequestMapping("/house/toAdd")
    public String toAdd(ModelMap modelMap) {
        modelMap.put("citys", cityService.getAllCitys());
        modelMap.put("communitys", houseService.getAllCommunitys());
        return "/house/add";
    }

    @RequestMapping("/house/add")
    public String doAdd(HouseVo house) {
        User user = UserContext.getUser();
        house.setState(CommonConstants.HOUSE_STATE_UP);
        houseService.addHouse(house, user);
        return "redirect:/house/ownlist";
    }

    @RequestMapping("house/ownlist")
    public String ownlist(HouseVo house, Integer pageNum, Integer pageSize, ModelMap modelMap) {
        User user = UserContext.getUser();
        house.setUserId(user.getId());
        house.setBookmarked(false);
        PageData<HouseVo> data = houseService.queryHouse(house, PageParams.build(pageSize, pageNum));
        modelMap.put("ps", data);
        modelMap.put("pageType", "own");
        return "/house/ownlist";
    }
}
