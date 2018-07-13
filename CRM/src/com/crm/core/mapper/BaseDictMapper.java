package com.crm.core.mapper;

import com.crm.core.pojo.BaseDict;

import java.util.List;

public interface BaseDictMapper {
    List<BaseDict> queryBaseDictByDictTypeCode(String dictTypeCode);
}
