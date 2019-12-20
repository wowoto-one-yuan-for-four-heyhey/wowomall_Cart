package com.xmu.wowomall.cart.service.impl;


import com.xmu.wowomall.cart.dao.CartDao;
import com.xmu.wowomall.cart.domain.CartItem;
import com.xmu.wowomall.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public CartItem getCartItemById(Integer id) {
        return cartDao.getCartItemById(id);
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

    @Override
    public CartItem fastAddCartItem(CartItem cartItem){
        CartItem existCartItem = cartDao.getCartItemsByUserIdAndProductId(cartItem.getUserId(), cartItem.getProductId());
        if(null != existCartItem) {
            existCartItem.setNumber(cartItem.getNumber());
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
    public List<CartItem> getCartItemsByUserId(Integer userId){
        return cartDao.getCartItemsByUserId(userId);
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