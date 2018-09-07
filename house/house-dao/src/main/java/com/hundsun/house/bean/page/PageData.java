package com.hundsun.house.bean.page;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageData<T> {
    private List<T> list;
    private Pagination pagination;

    public PageData(List<T> list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public static <T> PageData<T> buildPage(List<T> list, Long count, Integer pageSize, Integer pageNum) {
        return new PageData<>(list, new Pagination(pageSize, pageNum, count));
    }
}
