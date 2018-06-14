package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.BaseData;
import com.jd.mobile.domin.Cart;
import com.jd.mobile.domin.CartItem;
import com.jd.mobile.domin.Product;
import com.jd.mobile.service.CartService;
import com.jd.mobile.service.impl.CartServiceImpl;
import com.jd.mobile.service.ProductService;
import com.jd.mobile.service.impl.ProductServiceImpl;
import com.jd.mobile.utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "cart", urlPatterns = {"/cart/add", "/cart", "/cart/clear", "/cart/delete"})
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (TextUtils.equals(requestURI, "/cart")) {
            try {
                CartService service = new CartServiceImpl();
                Cart cart = service.getCart();
                BaseData<Cart> baseData = new BaseData<>(1, cart, "获取购物车成功");
                response.getWriter().write(JSON.toJSONString(baseData));
            } catch (Exception e) {
                e.printStackTrace();
                BaseData<Cart> baseData = new BaseData<>(1, null, e.getMessage());
                response.getWriter().write(JSON.toJSONString(baseData));
            }
        } else if (TextUtils.equals(requestURI, "/cart/delete")) {
            try {
                String pid = request.getParameter("pid");
                CartService service = new CartServiceImpl();
                service.delete(pid);
                BaseData<String> baseData = new BaseData<>(1, "", "删除成功");
                response.getWriter().write(JSON.toJSONString(baseData));
            } catch (Exception e) {
                e.printStackTrace();
                BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
                response.getWriter().write(JSON.toJSONString(baseData));
            }
        } else {
            response.setStatus(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (!TextUtils.equals(requestURI, "/cart/add")) {
            response.setStatus(404);
        } else {
            try {
                String pid = request.getParameter("pid");
                int count = Integer.parseInt(request.getParameter("count"));
                ProductService productService = new ProductServiceImpl();
                Product product = productService.getProductDetail(pid);
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setNum(count);
                CartService service = new CartServiceImpl();
                service.add(cartItem);
                BaseData<String> baseData = new BaseData<>(1, "", "添加购物车成功");
                response.getWriter().write(JSON.toJSONString(baseData));
            } catch (Exception e) {
                e.printStackTrace();
                BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
                response.getWriter().write(JSON.toJSONString(baseData));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (TextUtils.equals(requestURI, "/cart/clear")) {
            try {
                CartService service = new CartServiceImpl();
                service.clear();
                BaseData<String> baseData = new BaseData<>(1, "", "清空购物车成功");
                response.getWriter().write(JSON.toJSONString(baseData));
            } catch (Exception e) {
                e.printStackTrace();
                BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
                response.getWriter().write(JSON.toJSONString(baseData));
            }
        } else {
            response.setStatus(404);
        }
    }
}
