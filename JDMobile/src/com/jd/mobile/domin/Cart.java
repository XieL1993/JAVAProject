package com.jd.mobile.domin;

import com.jd.mobile.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private double total = 0;
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotal() {
        total = 0;
        for (CartItem item : items) {
            total += item.getSubTotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void add(CartItem cartItem) {
        String pid = cartItem.getProduct().getPid();
        boolean isExist = false;
        for (CartItem item : items) {
            if (TextUtils.equals(item.getProduct().getPid(), pid)) {
                isExist = true;
                item.setNum(item.getNum() + cartItem.getNum());
                break;
            }
        }
        if (!isExist) {
            items.add(cartItem);
        }
    }

    public int removeCartItem(String pid) {
        int flag = -1;
        for (int i = 0; i < items.size(); i++) {
            if (TextUtils.equals(items.get(i).getProduct().getPid(), pid)) {
                flag = i;
                break;
            }
        }
        if (flag != -1) {
            items.remove(flag);
        }
        return flag;
    }

    public void clearCart() {
        items.clear();
    }
}
