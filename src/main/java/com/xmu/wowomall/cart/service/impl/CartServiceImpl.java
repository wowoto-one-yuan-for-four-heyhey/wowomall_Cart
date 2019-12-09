package com.xmu.wowomall.cart.service.impl;


import com.xmu.wowomall.cart.dao.CartDao;
import com.xmu.wowomall.cart.domain.WowoCartItem;
import com.xmu.wowomall.cart.service.CartService;
import com.xmu.wowomall.cart.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author wowoto
 * @date 12/08/2019
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public WowoCartItem findCartItemById(Integer id) {
        return null;
    }

    @Override
    public void clearCartItem(List<WowoCartItem> wowoCartItems) {

    }

    @Override
    public Object addCartItem(WowoCartItem wowoCartItem){
        WowoCartItem existCartItem = cartDao.getCartItemsByUserIdAndProductId(wowoCartItem.getUserId(), wowoCartItem.getProductId());
        if(null != existCartItem) {
            existCartItem.setNumber(existCartItem.getNumber() + wowoCartItem.getNumber());
            cartDao.updateCartItem(existCartItem);
            return existCartItem;
        }
        cartDao.addCartItem(wowoCartItem);
        return wowoCartItem;
    }

    /**
     * 获取用户订单列表
     *
     * @param userId   用户ID
     *                 1未付款，
     *                 2未发货，
     *                 3未收货，
     *                 4未评价，
     *                 5已完成订单，
     *                 6退货订单，
     *                 7换货订单
     * @return 订单列表
     */
    @Override
    public Object getCarts(Integer userId){
        List<WowoCartItem> wowoCartItemList = cartDao.getCartItems(userId);
        List<Map<String, Object>> wowoCartsVoList = new ArrayList<>(wowoCartItemList.size());
        for(WowoCartItem oneCart:wowoCartItemList)
        {
            Map<String, Object> wowoCartVo = new HashMap<>();
            wowoCartVo.put("id",oneCart.getId());
            wowoCartVo.put("productId",oneCart.getProductId());
            wowoCartVo.put("num",oneCart.getNumber());
            wowoCartVo.put("beCheck",oneCart.getBeCheck());
            wowoCartsVoList.add(wowoCartVo);
        }
        return ResponseUtil.ok(wowoCartsVoList);
    }

    /**
     * 获取用户订单列表
     *
     * @param userId   用户ID
     * @param id       订单id
     * @param goodsId   goodsID
     * @param productId 产品ID
     * @param number    num
     * @return 订单列表
     */
    @Override
    public Object updateCartItem(Integer userId,Integer id,Integer goodsId,Integer productId,Integer number){

        WowoCartItem cartItem = cartDao.getCartItemById(id);
        if(cartItem == null){
            return ResponseUtil.fail();
        }
        cartItem.setNumber(number);
        cartItem.setProductId(productId);
        cartItem.setUserId(userId);
        Integer num = cartDao.updateCartItem(cartItem);
        if (num == 1){
            return ResponseUtil.ok(num);
        }
        return ResponseUtil.fail();
    }

    @Override
    public Object deleteCartItem(Integer userId,Integer productId){
        WowoCartItem cartItem = cartDao.getCartItemsByUserIdAndProductId(userId,productId);
        if(cartItem == null){
            return ResponseUtil.fail();
        }
        Integer num = cartDao.deleteCartItemBy(cartItem.getId());
        if (!num.equals(1)) {
            return ResponseUtil.fail();
        }

        return ResponseUtil.ok();
    }
}
