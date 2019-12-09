package com.xmu.wowomall.cart.controller;


import com.xmu.wowomall.cart.controller.vo.SubmitCartVo;
import com.xmu.wowomall.cart.service.CartService;
import com.xmu.wowomall.cart.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value="carts",tags = "购物车")
@RestController
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    /**
     * 获取购物车明细/index
     *
     * @param userId   用户ID
     * @param page     分页页数
     * @param limit     分页大小
     * @param sort      以什么为序
     * @param order     升/降序
     * @return 订单列表
     */
    @GetMapping("carts")
    @ApiOperation(value = "用户获取购物车列表/list", notes = "用户获取订单列表")
    public Object getCarts(Integer userId,
                            @ApiParam(name="page",value="页码",required=true) @RequestParam(defaultValue = "1")Integer page,
                            @ApiParam(name="limit",value="每页条数",required=true) @RequestParam(defaultValue = "10")Integer limit,
                            @ApiParam(name="sort",value="以什么为序",required=true) @RequestParam(defaultValue = "add_time") String sort,
                            @ApiParam(name="order",value="升/降序",required=true) @RequestParam(defaultValue = "desc") String order)
    {
        if(null != userId) {
            return ResponseUtil.unlogin();
        }
        return cartService.getCarts(userId,page, limit, sort, order);
    }


    /**
     * 添加商品到购物车/add
     *
     * @param userId 用户ID
     * @param submitCartVo
     * @return 提交订单操作结果
     */
    @PostMapping("carts")
    public Object submit(Integer userId, @RequestBody SubmitCartVo submitCartVo){



        return ResponseUtil.ok(submitCartVo);
    }







}
