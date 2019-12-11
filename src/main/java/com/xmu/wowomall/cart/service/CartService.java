package com.xmu.wowomall.cart.service;
import com.xmu.wowomall.cart.domain.WowoCartItem;
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
    WowoCartItem findCartItemById(Integer id);

    /**
     * 清空购物车里的指定项目
     * @param wowoCartItems 待清空的项目
     * @return 清空购物车
     */
    void clearCartItem(List<WowoCartItem> wowoCartItems);

    /**
     *  添加 商品到购物车 /add
     *  @param wowoCartItem 待添加的购物车项目
     *  @return 添加成功与否
     */
    Object addCartItem(WowoCartItem wowoCartItem);

    /**
     * 获取用户订单列表
     *
     * @param userId   用户ID
     * @return 订单列表
     */
    public Object getCarts(Integer userId);

    /**
     * 修改购物车商品货品数量
     *
     * @param userId   用户ID
     * @param id id
     * @param productId 产品ID
     * @param goodsId 商品Id
     * @param number 数量
     * @return 订单列表
     */
    public Object updateCartItem(Integer userId,Integer id,Integer goodsId,Integer productId,Integer number);

    /**
     * 删除购物车商品
     *
     * @param userId   用户ID
     * @param productId  商品ID
     * @return 订单列表
     */
    public Object deleteCartItem(Integer userId,Integer productId);
}
