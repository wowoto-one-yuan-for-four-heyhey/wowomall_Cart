package com.xmu.wowomall.cart.mapper;


import com.xmu.wowomall.cart.domain.WowoCartItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author wowoto
 * @date 12/09/2019
 */

@Repository
@Mapper
public interface CartMapper {

    /**
     * 根据订单查询信息返回订单对象
     * @param userId 用户id
     * @return 订单
     */
    List<WowoCartItem> getCartItemsByUserId(Integer userId);

    /**
     * 根据订单查询信息返回订单对象
     * @param productId 产品ID
     * @param userId 用户id
     * @return 订单
     */
    WowoCartItem getCartItemByUserIdAndProductId(Integer userId, Integer productId);

    /**
     *  根据订单查询信息返回订单对象
     * @param cartId 购物车id
     * @return 订单
     */
    WowoCartItem getCartItemById(Integer cartId);

    /**
     *  根据订单更新信息
     * @param wowoCartItem cartItem
     * @return 订单
     */
    Integer updateCartItemById(WowoCartItem wowoCartItem);

    /**
     * 添加订单信息
     * @param wowoCartItem cartItem
     * @return 订单
     */
    Integer addCartItem(WowoCartItem wowoCartItem);

    /**
     * 删除订单更新信息
     * @param cartId 购物车ID
     * @return 订单
     */
    Integer deleteCartItemById(Integer cartId);
}
