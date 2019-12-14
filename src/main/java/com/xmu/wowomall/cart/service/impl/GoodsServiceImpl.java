package com.xmu.wowomall.cart.service.impl;

import com.xmu.wowomall.cart.domain.Product;
import com.xmu.wowomall.cart.service.GoodsService;
import com.xmu.wowomall.cart.service.RemoteGoodsService;
import com.xmu.wowomall.cart.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Tens
 * @Description:
 * @Date: 2019/12/14 18:01
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    RemoteGoodsService remoteGoodsService;

    @Override
    public Product getProductById(Integer productId) {
        String json = remoteGoodsService.getProductById(productId);
        return JacksonUtil.parseObject(json, "data", Product.class);
    }
}
