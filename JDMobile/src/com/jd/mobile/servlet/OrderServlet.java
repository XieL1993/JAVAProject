package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.*;
import com.jd.mobile.service.CartService;
import com.jd.mobile.service.OrderService;
import com.jd.mobile.service.UserService;
import com.jd.mobile.service.impl.CartServiceImpl;
import com.jd.mobile.service.impl.OrderServiceImpl;
import com.jd.mobile.service.impl.UserServiceImpl;
import com.jd.mobile.utils.ResponseResult;
import com.jd.mobile.utils.TextUtils;
import com.jd.mobile.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "order", urlPatterns = {"/order/add", "/order", "/order/detail", "/order/pay", "/order/delete"})
public class OrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (TextUtils.equals(requestURI, "/order/add")) {
            addOrder(request, response);
        } else if (TextUtils.equals(requestURI, "/order/pay")) {
            pay(request, response);
        } else if (TextUtils.equals(requestURI, "/order/delete")) {
            delete(request, response);
        } else {
            response.setStatus(404);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (TextUtils.equals(requestURI, "/order")) {
            getOrderList(request, response);
        } else if (TextUtils.equals(requestURI, "/order/detail")) {
            getOrderDetail(request, response);
        } else {
            response.setStatus(404);
        }
    }

    private void getOrderList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String uid = request.getParameter("uid");
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            UserService service = new UserServiceImpl();
            User user = service.find(uid);
            if (user == null) {
                ResponseResult.error(response, new Exception("未登录"));
            } else {
                OrderService orderService = new OrderServiceImpl();
                PageBean<Order> pageBean = orderService.getOrderList(user, pageSize, currentPage);
                ResponseResult.success(response, pageBean, "获取商品列表成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseResult.error(response, e);
        }
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取用户
            String uid = request.getParameter("uid");
            UserService service = new UserServiceImpl();
            User user = service.find(uid);
            if (user == null) {
                BaseData<String> baseData = new BaseData<>(403, "", "未登录");
                response.getWriter().write(JSON.toJSONString(baseData));
                return;
            }
            // 获取购物车
            CartService cartService = new CartServiceImpl();
            Cart cart = cartService.getCart();
            if (cart == null || cart.getItems() == null || cart.getItems().size() == 0) {
                BaseData<String> baseData = new BaseData<>(0, "", "购物车为空");
                response.getWriter().write(JSON.toJSONString(baseData));
                return;
            }
            // 创建订单
            Order order = new Order();
            order.setOid(UUIDUtils.getUUID());
            order.setOrdertime(new Date());
            order.setTotal(cart.getTotal());
            order.setState(1);
            order.setUser(user);
            // 创建订单子项
            List<CartItem> items = cart.getItems();
            for (CartItem item : items) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItemid(UUIDUtils.getUUID());
                orderItem.setQuantity(item.getNum());
                orderItem.setTotal(item.getSubTotal());
                orderItem.setProduct(item.getProduct());
                orderItem.setOid(order.getOid());
                order.getList().add(orderItem);
            }
            // 提交订单
            OrderService orderService = new OrderServiceImpl();
            orderService.saveOrder(order);
            //  提交订单成功之后清空购物车
            cartService.clear();
            BaseData<String> baseData = new BaseData<>(1, "", "提交订单成功");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (Exception e) {
            e.printStackTrace();
            BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String oid = request.getParameter("oid");
            OrderService service = new OrderServiceImpl();
            Order order = service.getOrderDetail(oid);
            if (order == null) {
                ResponseResult.error(response, new Exception("订单不存在"));
            } else {
                service.deleteOrder(order);
                ResponseResult.success(response, "", "删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult.error(response, e);
        }
    }

    private void getOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String oid = request.getParameter("oid");
            OrderService service = new OrderServiceImpl();
            Order orderDetail = service.getOrderDetail(oid);
            ResponseResult.success(response, orderDetail, "获取订单详情成功");
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseResult.error(response, e);
        }

    }

    private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String oid = request.getParameter("oid");
            String address = request.getParameter("address");
            String name = request.getParameter("name");
            String telephone = request.getParameter("telephone");
            OrderService service = new OrderServiceImpl();
            Order order = service.getOrderDetail(oid);
            if (oid == null) {
                ResponseResult.error(response, new Exception("订单不存在"));
            } else {
                order.setAddress(address);
                order.setName(name);
                order.setTelephone(telephone);
                order.setState(2);
                service.updateOrder(order);
                ResponseResult.success(response, "", "支付成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseResult.error(response, e);
        }
    }
}
