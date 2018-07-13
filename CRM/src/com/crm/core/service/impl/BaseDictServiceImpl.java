package com.crm.core.service.impl;

import com.crm.core.mapper.BaseDictMapper;
import com.crm.core.pojo.BaseDict;
import com.crm.core.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDictServiceImpl implements BaseDictService {
    @Autowired
    private BaseDictMapper mapper;

    @Override
    public List<BaseDict> queryBaseDictByDictTypeCode(String dictTypeCode) {
        return mapper.queryBaseDictByDictTypeCode(dictTypeCode);
    }
}
