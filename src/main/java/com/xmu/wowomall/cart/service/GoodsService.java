package com.xmu.wowomall.cart.service;

import com.xmu.wowomall.cart.domain.Product;
import org.springframework.stereotype.Service;

/**
 * @Author: Tens
 * @Description:
 * @Date: 2019/12/14 18:00
 */
@Service
public interface GoodsService {

    Product getProductById(Integer productId);
}
