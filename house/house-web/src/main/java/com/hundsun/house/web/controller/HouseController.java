package com.hundsun.house.web.controller;

import com.hundsun.house.bean.HouseUser;
import com.hundsun.house.bean.UserMsg;
import com.hundsun.house.bean.page.PageData;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.CommentVo;
import com.hundsun.house.bean.vo.HouseVo;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.constants.CommonConstants;
import com.hundsun.house.result.ResultMsg;
import com.hundsun.house.service.AgencyService;
import com.hundsun.house.service.CommentService;
import com.hundsun.house.service.HouseService;
import com.hundsun.house.service.RecommendService;
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
        return "redirect:/house/detail?id=" + userMsg.getHouseId() +"&"+ ResultMsg.successMsg("留言成功").asUrlParams();
    }
}
