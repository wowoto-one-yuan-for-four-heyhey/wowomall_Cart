package com.xmu.wowomall.cart.dao;


import com.xmu.wowomall.cart.domain.WowoCartItem;
import com.xmu.wowomall.cart.mapper.CartMapper;
import com.xmu.wowomall.cart.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 *
 * @author wowoto
 * @date 12/08/2019
 */

@Repository
public class CartDao {

    @Autowired
    private CartMapper cartMapper;

    /**
     * 根据订单Id信息返回订单物品列表
     * @return 订单物品列表
     */
    public List<WowoCartItem> getCartItems(Integer userId)
    {
        List<WowoCartItem> wowoCartItemList = cartMapper.getCartItemsByUserId(userId);
        return wowoCartItemList;
    }

    public WowoCartItem getCartItemsByUserIdAndProductId(Integer userId, Integer productId){
        WowoCartItem wowoCartItem = cartMapper.getCartItemByUserIdAndProductId(userId, productId);
        return wowoCartItem;
    }

    public WowoCartItem getCartItemById(Integer cartId){
        WowoCartItem wowoCartItem = cartMapper.getCartItemById(cartId);
        return wowoCartItem;
    }

    public Integer addCartItem(WowoCartItem wowoCartItem){

        return cartMapper.addCartItem(wowoCartItem);
    }

    /**
     * 获取用户订单列表
     * @param wowoCartItem 购物车项
     * @return 订单列表
     */
    public Integer updateCartItem(WowoCartItem wowoCartItem){
        return  cartMapper.updateCartItemById(wowoCartItem);
    }


    /**
     * 获取用户订单列表
     * @param cartId 购物车id
     * @return 订单列表
     */
    public Integer deleteCartItemById(Integer cartId){

        return cartMapper.deleteCartItemById(cartId);
    }




}
