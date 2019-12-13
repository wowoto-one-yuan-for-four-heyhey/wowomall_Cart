package com.xmu.wowomall.cart.service;

import com.xmu.wowomall.cart.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Tens
 * @Description:
 * @Date: 2019/12/12 22:56
 */
@Service
@FeignClient("goods")
public interface GoodsService {
    @GetMapping("product/{id}")
    Product getProductById(@PathVariable("id") Integer productId);
}
