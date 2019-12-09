package com.xmu.wowomall.cart.controller;


import com.xmu.wowomall.cart.domain.WowoCartItem;
import com.xmu.wowomall.cart.service.CartService;
import com.xmu.wowomall.cart.util.JacksonUtil;
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
        if(null == userId) {
            return ResponseUtil.unlogin();
        }
        return cartService.getCarts(userId,page, limit, sort, order);
    }


    /**
     * 加入商品到购物车
     * <p>
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param userId 用户ID
     * @param cart   购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     */
    @PostMapping("carts")
    @ApiOperation(value = "添加商品到购物车 /add")
    public Object add( Integer userId, @RequestBody String cart) {
        if(null == userId)
            ResponseUtil.unlogin();

        Integer goodsId = JacksonUtil.parseInteger(cart, "goodsId");
        Integer productId = JacksonUtil.parseInteger(cart, "productId");
        Integer number = JacksonUtil.parseInteger(cart, "number");

        if(null == goodsId || null == productId || null == number)
            ResponseUtil.badArgument();

        return null;
    }

    /**
     * 立即购买
     * <p>
     * 和add方法的区别在于：
     * 1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖
     * 2. 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID
     *
     * @param userId 用户ID
     * @param cart   购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 立即购买操作结果
     */
    @PostMapping("carts/{id}")
    @ApiOperation(value = "添加一个购物车明细，如果已经存在这种商品，就进行数量覆盖，否则就新增购物车明细 /fastadd")
    public Object fastadd( Integer userId, @RequestBody String cart){
        return null;
    }

    /**
     * 修改购物车商品货品数量
     *
     * @param userId 用户ID
     * @param cart   购物车商品信息， { id: xxx, goodsId: xxx, productId: xxx, number: xxx }
     * @return 修改结果
     */
    @PutMapping("carts/{id}")
    @ApiOperation(value = "修改某个购物车项信息 /update")
    public Object update(Integer userId, @RequestBody String cart){
        return null;
    }

    /**
     * 购物车商品删除
     *
     * @param userId 用户ID
     * @param body   购物车商品信息， { productIds: xxx }
     * @return 购物车信息
     * 成功则
     * {
     * errno: 0,
     * errmsg: '成功',
     * data: xxx
     * }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @DeleteMapping("carts/{id}")
    @ApiOperation(value = "删除一个购物车项 /delete")
    public Object delete(Integer userId, @RequestBody String body){
        return null;
    }
}
