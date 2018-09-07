package com.hundsun.house.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.HouseVo;
import com.hundsun.house.service.HouseService;
import com.hundsun.house.service.RecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendServiceImpl implements RecommendService {
    private static final String HOT_HOUSE_KEY = "hot_house";
    private static final Logger logger = LoggerFactory.getLogger(RecommendServiceImpl.class);
    @Autowired
    private HouseService houseService;

    @Override
    public void increase(Long id) {
        try {
            Jedis jedis = new Jedis("127.0.0.1");
            jedis.zincrby(HOT_HOUSE_KEY, 1d, id + "");
            jedis.zremrangeByRank(HOT_HOUSE_KEY, 10, -1l);
            jedis.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public List<Long> getHot() {
        try {
            Jedis jedis = new Jedis("127.0.0.1");
            Set<String> idSet = jedis.zrevrange(HOT_HOUSE_KEY, 0, -1);
            jedis.close();
            List<Long> ids = idSet.stream().map(Long::parseLong).collect(Collectors.toList());
            return ids;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Lists.newArrayList();
        }
    }

    @Override
    public List<HouseVo> getHotHouse(Integer size) {
        List<Long> list = getHot();
        list = list.subList(0, Math.min(list.size(), size));
        if (list.isEmpty()) {
            return Lists.newArrayList();
        }
        HouseVo houseVo = new HouseVo();
        houseVo.setIds(list);
        List<HouseVo> houses = houseService.queryAndSetImg(houseVo, PageParams.build(size, 1));
        final List<Long> order = list;
        Ordering<HouseVo> houseSort = Ordering.natural().onResultOf(hs -> order.indexOf(hs.getId()));
        return houseSort.sortedCopy(houses);
    }

    @Override
    public List<HouseVo> getLastest() {
        return houseService.queryAndSetImg(new HouseVo(), PageParams.build(8, 1));
    }
}
