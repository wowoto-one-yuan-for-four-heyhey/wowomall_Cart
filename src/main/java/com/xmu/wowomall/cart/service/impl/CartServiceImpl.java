package com.xmu.wowomall.cart.service.impl;


import com.xmu.wowomall.cart.dao.CartDao;
import com.xmu.wowomall.cart.domain.CartItem;
import com.xmu.wowomall.cart.service.CartService;
import com.xmu.wowomall.cart.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author wowoto
 * @date 12/08/2019
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public CartItem findCartItemById(Integer id) {
        return null;
    }

    @Override
    public CartItem addCartItem(CartItem cartItem){
        CartItem existCartItem = cartDao.getCartItemsByUserIdAndProductId(cartItem.getUserId(), cartItem.getProductId());
        if(null != existCartItem) {
            existCartItem.setNumber(existCartItem.getNumber() + cartItem.getNumber());
            cartDao.updateCartItem(existCartItem);
            return existCartItem;
        }
        cartDao.addCartItem(cartItem);
        return cartItem;
    }

    /**
     * 获取用户订单列表
     *
     * @param userId   用户ID
     *                 1未付款，
     *                 2未发货，
     *                 3未收货，
     *                 4未评价，
     *                 5已完成订单，
     *                 6退货订单，
     *                 7换货订单
     * @return 订单列表
     */
    @Override
    public List<CartItem> getCartItems(Integer userId){
        return cartDao.getCartItems(userId);
    }

    /**
     * 获取用户订单列表
     *
     * @param cartItem
     * @return 订单列表
     */
    @Override
    public CartItem updateCartItem(CartItem cartItem){
        cartDao.updateCartItem(cartItem);
        return cartItem;
    }

    @Override
    public Integer deleteCartItem(Integer cartItemId){
        return cartDao.deleteCartItemById(cartItemId);
    }
}