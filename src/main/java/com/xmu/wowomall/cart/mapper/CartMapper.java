package com.xmu.wowomall.cart.mapper;


import com.xmu.wowomall.cart.domain.CartItem;
import com.xmu.wowomall.cart.domain.po.CartItemPo;
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
     * 根据userID拿到User
     * @param userId
     * @return
     */
    List<CartItem> getCartItemsByUserId(Integer userId);

    /**
     * 根据id拿到cartItem
     * @param cartId
     * @return
     */
    CartItem getCartItemById(Integer cartId);

    /**
     * 555
     * @param userId
     * @param productId
     * @return
     */
    CartItemPo getCartItemByUserIdAndProductId(Integer userId, Integer productId);

    /**
     *
     * @param cartId
     * @return
     */
    CartItemPo getCartItemPoById(Integer cartId);

    /**
     * 555
     * @param cartItem
     * @return
     */
    Integer updateCartItemById(CartItemPo cartItem);

    /**
     * 555
     * @param cartItem
     * @return
     */
    Integer addCartItem(CartItemPo cartItem);

    /**
     *  555
     * @param cartId
     * @return
     */
    Integer deleteCartItemById(Integer cartId);
}
