package com.xmu.wowomall.cart.service.impl;


import com.xmu.wowomall.cart.dao.CartDao;
import com.xmu.wowomall.cart.domain.WowoCartItem;
import com.xmu.wowomall.cart.service.CartService;
import com.xmu.wowomall.cart.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addCartItem(WowoCartItem wowoCartItems){

    }


    @Override
    public Object getCarts(Integer userId, Integer page, Integer limit, String sort, String order){

        List<WowoCartItem> wowoCartItemList = cartDao.getCartItem(userId,page, limit, sort, order);
        return ResponseUtil.ok(wowoCartItemList);
    }
}
