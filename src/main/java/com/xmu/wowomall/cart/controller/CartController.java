package com.xmu.wowomall.cart.controller;


import com.xmu.wowomall.cart.dao.CartDao;
import com.xmu.wowomall.cart.domain.CartItem;
import com.xmu.wowomall.cart.service.CartService;
import com.xmu.wowomall.cart.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value="carts",tags = "购物车")
@RestController
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDao cartDao;

    /**
     * 用户购物车信息
     *
     * @return List<CartItem>   用户购物车信息
     */
    @GetMapping("cartItems")
    @ApiOperation(value = "用户获取购物车列表/list", notes = "用户获取订单列表")
    public Object getCartItems()
    {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        List<CartItem> cartItems = cartService.getCartItems(userId);
        return ResponseUtil.ok(cartItems);
    }

    /**
     * 加入商品到购物车
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param cartItem
     * @return cartItem
     */
    @PostMapping("cartItems")
    @ApiOperation(value = "添加商品到购物车")
    public Object add(@RequestParam CartItem cartItem) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        if(userId != cartItem.getUserId()){
            ResponseUtil.unauthz();
        }

        cartItem = cartService.addCartItem(cartItem);
        return ResponseUtil.ok(cartItem);
    }

    /**
     * 修改购物车商品货品数量
     *
     * @param cartItem   购物车商品信息
     * @return cartItem
     */
    @PutMapping("cartItems/{id}")
    @ApiOperation(value = "修改某个购物车项信息 /update")
    public Object update(@PathVariable("id")Integer cartItemId, @RequestParam CartItem cartItem){
        Integer userId = Integer.valueOf(request.getHeader("id"));
        if(userId != cartItem.getUserId()){
            return ResponseUtil.unauthz();
        }
        if(cartItemId != cartItem.getId()){
            return ResponseUtil.badArgument();
        }

        cartItem = cartService.updateCartItem(cartItem);
        return ResponseUtil.ok(cartItem);
    }

    /**
     * 购物车商品删除
     *
     * @param cartItemId   购物车商品信息
     * @return 购物车信息
     */
    @DeleteMapping("cartItemId/{id}")
    @ApiOperation(value = "删除一个购物车项 /delete")
    public Object delete(@PathVariable("id")Integer cartItemId){
        Integer userId = Integer.valueOf(request.getHeader("id"));
        CartItem cartItem = cartDao.getCartItemById(cartItemId);

        if(userId != cartItem.getUserId()){
            return ResponseUtil.unauthz();
        }
        if(cartItemId != cartItem.getId()){
            return ResponseUtil.badArgument();
        }

        cartService.deleteCartItem(cartItemId);
        return ResponseUtil.ok();
    }
}