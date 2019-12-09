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
     */
    void clearCartItem(List<WowoCartItem> wowoCartItems);

    /**
     *  添加 商品到购物车 /add
     *  @param wowoCartItems 待添加的购物车项目
     */
    void addCartItem(WowoCartItem wowoCartItems);


    /**
     * 获取用户订单列表
     *
     * @param userId   用户ID
     * @param page     分页页数
     * @param limit     分页大小
     * @param sort      排序
     * @param order     正序/逆序
     * @return 订单列表
     */
    public Object getCarts(Integer userId, Integer page, Integer limit, String sort, String order);



}