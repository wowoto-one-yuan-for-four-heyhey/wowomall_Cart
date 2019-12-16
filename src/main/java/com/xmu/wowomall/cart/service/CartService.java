package com.xmu.wowomall.cart.service;
import com.xmu.wowomall.cart.domain.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author wowoto
 * @date 12/08/2019
 */
@Service
public interface CartService {
    /**
     * 用ID获得CartItem对象
     * @param id 对象ID
     * @return wowoCartItem对象
     */
    CartItem getCartItemById(Integer id);

    /**
     *  添加 商品到购物车 /add
     *  @param cartItem 待添加的购物车项目
     *  @return 添加成功与否
     */
    CartItem addCartItem(CartItem cartItem);

    /**
     * 获取用户订单列表
     *
     * @param userId   用户ID
     * @return 订单列表
     */
    public List<CartItem> getCartItemsByUserId(Integer userId);

    /**
     * 修改购物车商品货品数量
     *
     * @param cartItem cartItem
     * @return cartItem
     */
    public CartItem updateCartItem(CartItem cartItem);

    /**
     * 删除购物车商品
     *
     * @param cartItemId
     * @return 订单列表
     */
    public boolean deleteCartItem(Integer cartItemId);
}
