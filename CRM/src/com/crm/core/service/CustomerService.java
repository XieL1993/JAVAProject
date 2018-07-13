package com.crm.core.service;

import com.crm.common.utils.Page;
import com.crm.core.pojo.Customer;
import com.crm.core.pojo.QueryVo;

public interface CustomerService {
    Page<Customer> queryCustomerByQueryVo(QueryVo queryVo);

    Customer queryCustomerById(Long id);

    void updateCustomerById(Customer customer);

    void deleteCustomerById(Long id);
}
