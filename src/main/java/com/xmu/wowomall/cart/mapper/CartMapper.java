package com.xmu.wowomall.cart.mapper;


import com.xmu.wowomall.cart.domain.CartItem;
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


     * @return 订单
     */

    List<CartItem> getCartItemsByUserId(Integer userId);

    CartItem getCartItemByUserIdAndProductId(Integer userId, Integer productId);

    CartItem getCartItemById(Integer cartId);

    Integer updateCartItemById(CartItem cartItem);

    Integer addCartItem(CartItem cartItem);

    Integer deleteCartItemById(Integer cartId);
}
