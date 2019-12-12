package com.xmu.wowomall.cart.service;

import com.xmu.wowomall.cart.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: Tens
 * @Description:
 * @Date: 2019/12/12 22:56
 */
@Service
@FeignClient("wowoto-goods")
public interface GoodsService {
    @GetMapping("product/{id}")
    Product getProductById(Integer productId);
}
