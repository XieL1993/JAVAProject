package com.crm.core.controller;

import com.crm.common.utils.Page;
import com.crm.core.pojo.BaseDict;
import com.crm.core.pojo.Customer;
import com.crm.core.pojo.QueryVo;
import com.crm.core.service.BaseDictService;
import com.crm.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Value("${CUSTOMER_FROM_TYPE}")
    private String CUSTOMER_FROM_TYPE;
    @Value("${CUSTOMER_INDUSTRY_TYPE}")
    private String CUSTOMER_INDUSTRY_TYPE;
    @Value("${CUSTOMER_LEVEL_TYPE}")
    private String CUSTOMER_LEVEL_TYPE;
    @Autowired
    private BaseDictService baseDictService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("list")
    public String queryCustomerList(QueryVo queryVo, Model model) throws UnsupportedEncodingException {
        if (!StringUtils.isEmpty(queryVo.getCustName())) {
            queryVo.setCustName(new String(queryVo.getCustName().getBytes("ISO-8859-1"), "UTF-8"));
        }
        // 客户来源
        List<BaseDict> fromType = baseDictService.queryBaseDictByDictTypeCode(CUSTOMER_FROM_TYPE);
        // 所属行业
        List<BaseDict> industryType = baseDictService.queryBaseDictByDictTypeCode(CUSTOMER_INDUSTRY_TYPE);
        // 客户级别
        List<BaseDict> levelType = baseDictService.queryBaseDictByDictTypeCode(CUSTOMER_LEVEL_TYPE);
        model.addAttribute("fromType", fromType);
        model.addAttribute("industryType", industryType);
        model.addAttribute("levelType", levelType);

        Page<Customer> page = customerService.queryCustomerByQueryVo(queryVo);
        model.addAttribute("page", page);
        // 数据回显
        model.addAttribute("custName", queryVo.getCustName());
        model.addAttribute("custSource", queryVo.getCustSource());
        model.addAttribute("custIndustry", queryVo.getCustIndustry());
        model.addAttribute("custLevel", queryVo.getCustLevel());
        return "customer";
    }

    @RequestMapping("edit")
    @ResponseBody
    public Customer queryCustomerById(Long id) {
        return customerService.queryCustomerById(id);
    }

    @RequestMapping("update")
    @ResponseBody
    public String updateCustomerById(Customer customer) {
        customerService.updateCustomerById(customer);
        return "OK";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String deleteCustomerById(Long id) {
        customerService.deleteCustomerById(id);
        return "OK";
    }
}
