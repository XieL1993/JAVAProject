package com.jd.mobile.service.impl;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.Cart;
import com.jd.mobile.domin.CartItem;
import com.jd.mobile.service.CartService;
import com.jd.mobile.utils.JedisUtils;
import com.jd.mobile.utils.TextUtils;
import redis.clients.jedis.Jedis;

public class CartServiceImpl implements CartService {
    @Override
    public Cart getCart() throws Exception {
        Jedis jedis = JedisUtils.getJedis();
        Cart cart;
        String jsonStr = jedis.get("cart");
        if (!TextUtils.isEmpty(jsonStr)) {
            cart = JSON.parseObject(jsonStr, Cart.class);
        } else {
            cart = new Cart();
        }
        JedisUtils.closeJedis(jedis);
        return cart;
    }

    @Override
    public void add(CartItem cartItem) throws Exception {
        Jedis jedis = JedisUtils.getJedis();
        Cart cart;
        String jsonStr = jedis.get("cart");
        if (!TextUtils.isEmpty(jsonStr)) {
            cart = JSON.parseObject(jsonStr, Cart.class);
        } else {
            cart = new Cart();
        }
        cart.add(cartItem);
        jedis.set("cart", JSON.toJSONString(cart));
        JedisUtils.closeJedis(jedis);
    }

    @Override
    public void delete(String pid) throws Exception {
        Jedis jedis = JedisUtils.getJedis();
        Cart cart;
        String jsonStr = jedis.get("cart");
        if (!TextUtils.isEmpty(jsonStr)) {
            cart = JSON.parseObject(jsonStr, Cart.class);
        } else {
            cart = new Cart();
        }
        int flag = cart.removeCartItem(pid);
        if (flag != -1) {
            jedis.set("cart", JSON.toJSONString(cart));
        }
        JedisUtils.closeJedis(jedis);
    }

    @Override
    public void clear() throws Exception {
        Jedis jedis = JedisUtils.getJedis();
        Cart cart;
        String jsonStr = jedis.get("cart");
        if (!TextUtils.isEmpty(jsonStr)) {
            cart = JSON.parseObject(jsonStr, Cart.class);
        } else {
            cart = new Cart();
        }
        cart.clearCart();
        jedis.set("cart", JSON.toJSONString(cart));
        JedisUtils.closeJedis(jedis);
    }
}
