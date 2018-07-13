package com.crm.core.service.impl;

import com.crm.common.utils.Page;
import com.crm.core.mapper.CustomerMapper;
import com.crm.core.pojo.Customer;
import com.crm.core.pojo.QueryVo;
import com.crm.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper mapper;

    @Override
    public Page<Customer> queryCustomerByQueryVo(QueryVo queryVo) {
        queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());
        List<Customer> list = mapper.queryCustomerByQueryVo(queryVo);
        int total = mapper.queryCountByQueryVo(queryVo);
        Page<Customer> page = new Page<>();
        page.setPage(queryVo.getPage());
        page.setSize(queryVo.getRows());
        page.setTotal(total);
        page.setRows(list);
        return page;
    }

    @Override
    public Customer queryCustomerById(Long id) {
        return mapper.queryCustomerById(id);
    }

    @Override
    public void updateCustomerById(Customer customer) {
        mapper.updateCustomerById(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        mapper.deleteCustomerById(id);
    }
}
