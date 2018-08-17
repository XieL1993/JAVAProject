package com.javaee.learning.demo2.controller;

import com.javaee.learning.demo2.pojo.Orders;
import com.javaee.learning.demo2.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class OrderController {
    @Resource(name = "orderService")
    private OrderService orderService;

    @RequestMapping("/orders")
    public ModelAndView findAll() {
        List<Orders> orders = orderService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("itemList2");
        return modelAndView;
    }

    //    @RequestMapping("/orders/detail")
//    public ModelAndView findById(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
//        String oid = request.getParameter("oid");
//        Orders order = orderService.findByid(Integer.parseInt(oid));
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("order", order);
//        modelAndView.setViewName("editItem");
//        return modelAndView;
//    }
    @RequestMapping("/orders/detail")
    public ModelAndView findById(@RequestParam(value = "oid", required = false) Integer id) {
        Orders order = orderService.findByid(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.setViewName("editItem");
        return modelAndView;
    }

    @RequestMapping("/orders/update")
    public ModelAndView updateOrder(Orders order) {
        System.out.println(order);
        orderService.updateOrder(order);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
