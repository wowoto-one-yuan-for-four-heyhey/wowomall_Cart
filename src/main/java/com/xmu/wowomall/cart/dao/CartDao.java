package com.xmu.wowomall.cart.dao;


import com.xmu.wowomall.cart.domain.CartItem;
import com.xmu.wowomall.cart.mapper.CartMapper;
import com.xmu.wowomall.cart.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Autowired
    private GoodsService goodsService;

    /**
     * 根据订单Id信息返回订单物品列表
     * @return 订单物品列表
     */
    public List<CartItem> getCartItems(Integer userId)
    {
        List<CartItem> cartItemList = cartMapper.getCartItemsByUserId(userId);
        for (CartItem cartItem: cartItemList){
            cartItem.setProduct(goodsService.getProductById(cartItem.getProductId()));
        }
        return cartItemList;
    }

    public CartItem getCartItemsByUserIdAndProductId(Integer userId, Integer productId){
        CartItem cartItem = cartMapper.getCartItemByUserIdAndProductId(userId, productId);
        cartItem.setProduct(goodsService.getProductById(cartItem.getProductId()));
        return cartItem;
    }

    public CartItem getCartItemById(Integer cartId){
        CartItem cartItem = cartMapper.getCartItemById(cartId);
        cartItem.setProduct(goodsService.getProductById(cartItem.getProductId()));
        return cartItem;
    }

    public CartItem addCartItem(CartItem cartItem){
        cartMapper.addCartItem(cartItem);
        return cartItem;
    }

    /**
     * 获取用户订单列表
     * @param cartItem 购物车项
     * @return 订单列表
     */
    public Integer updateCartItem(CartItem cartItem){
        return  cartMapper.updateCartItemById(cartItem);
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
