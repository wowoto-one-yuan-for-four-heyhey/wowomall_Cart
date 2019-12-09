package com.xmu.wowomall.cart.dao;


import com.xmu.wowomall.cart.domain.WowoCartItem;
import com.xmu.wowomall.cart.mapper.CartMapper;
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

    /**
     * 根据订单Id信息返回订单物品列表
     * @return 订单物品列表
     */
    public List<WowoCartItem> getCartItem(Integer userId)
    {
        List<WowoCartItem> wowoCartItemList = cartMapper.getCartItem(userId);
        return wowoCartItemList;
    }




}
