package com.jd.mobile.service;

import com.jd.mobile.domin.Cart;
import com.jd.mobile.domin.CartItem;

public interface CartService {

    Cart getCart() throws Exception;

    void add(CartItem cartItem) throws Exception;

    void delete(String pid) throws Exception;

    void clear() throws Exception;
}
