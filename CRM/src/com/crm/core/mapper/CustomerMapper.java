package com.crm.core.mapper;

import com.crm.core.pojo.Customer;
import com.crm.core.pojo.QueryVo;

import java.util.List;

public interface CustomerMapper {
    List<Customer> queryCustomerByQueryVo(QueryVo queryVo);

    int queryCountByQueryVo(QueryVo queryVo);

    Customer queryCustomerById(Long id);

    void updateCustomerById(Customer customer);

    void deleteCustomerById(Long id);
}
