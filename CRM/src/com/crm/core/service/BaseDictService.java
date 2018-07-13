package com.crm.core.service;

import com.crm.core.pojo.BaseDict;

import java.util.List;

public interface BaseDictService {
    public List<BaseDict> queryBaseDictByDictTypeCode(String dictTypeCode);
}
