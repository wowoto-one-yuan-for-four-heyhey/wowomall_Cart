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


     * @return 订单
     */

    List<WowoCartItem> getCartItemsByUserId(Integer userId);

    WowoCartItem getCartItemByUserIdAndProductId(Integer userId, Integer productId);
    WowoCartItem getCartItemById(Integer cartId);
    Integer updateCartItemById(WowoCartItem wowoCartItem);

    Integer addCartItem(WowoCartItem wowoCartItem);
    Integer deleteCartItem(Integer cartId);
}
