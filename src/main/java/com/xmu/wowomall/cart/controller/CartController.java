package com.xmu.wowomall.cart.controller;

import com.xmu.wowomall.cart.dao.CartDao;
import com.xmu.wowomall.cart.domain.CartItem;
import com.xmu.wowomall.cart.service.CartService;
import com.xmu.wowomall.cart.util.ResponseUtil;
import io.swagger.annotations.Api;
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

    /**
     * 取得购物车中的一项
     *
     * @param cartItemId
     * @return cartItem
     */
    @GetMapping("cartItems/{id}")
    public Object findCartItemById(@PathVariable("id") Integer cartItemId) {
        CartItem cartItem = cartService.getCartItemById(cartItemId);
        if(cartItem == null){return ResponseUtil.fail();}
        return ResponseUtil.ok(cartItem);
    }

    /**
     * 用户购物车信息
     * @return List<CartItem>   用户购物车信息
     */
    @GetMapping("cartItems")
    public Object getCartItems()
    {
        if(request.getHeader("id") == null)
            return ResponseUtil.unlogin();
        Integer userId = Integer.valueOf(request.getHeader("id"));
        List<CartItem> cartItems = cartService.getCartItemsByUserId(userId);
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
    public Object addCartItem(@RequestBody CartItem cartItem) {
        if(request.getHeader("id") == null)
            return ResponseUtil.unlogin();

        Integer userId = Integer.valueOf(request.getHeader("id"));
        if(userId < 0){
            return ResponseUtil.unlogin();
        }
        if(cartItem.getUserId() == null || cartItem.getProductId() == null || cartItem.getNumber() == null){
            return ResponseUtil.badArgument();
        }
        if(!userId.equals(cartItem.getUserId())){
            return ResponseUtil.unauthz();
        }

        cartItem = cartService.addCartItem(cartItem);
        return ResponseUtil.ok(cartItem);
    }

    /**
     * 快速购买
     *
     * @param cartItem
     * @return cartItem
     */
    @PostMapping("fastAddCartItems")
    public Object fastAdd(@RequestBody CartItem cartItem) {
        if(request.getHeader("id") == null)
            return ResponseUtil.unlogin();

        Integer userId = Integer.valueOf(request.getHeader("id"));
        if(userId < 0){ return ResponseUtil.unlogin(); }
        if(cartItem.getProductId() == null || cartItem.getNumber() == null){ return ResponseUtil.badArgument(); }
        if(cartItem.getUserId() != null && !userId.equals(cartItem.getUserId())){ return ResponseUtil.unauthz(); }

        cartItem = cartService.fastAddCartItem(cartItem);
        return ResponseUtil.ok(cartItem);
    }

    /**
     * 修改购物车商品货品数量
     *
     * @param cartItem   购物车商品信息
     * @return cartItem
     */
    @PutMapping("cartItems/{id}")
    public Object update(@PathVariable("id")Integer cartItemId, @RequestBody CartItem cartItem){
        if(request.getHeader("id") == null)
            return ResponseUtil.unlogin();
        Integer userId = Integer.valueOf(request.getHeader("id"));
        if(userId != cartItem.getUserId()){
            return ResponseUtil.unauthz();
        }
        if(cartItem.getUserId() == null || cartItem.getProductId() == null || cartItem.getNumber() == null){
            return ResponseUtil.badArgument();
        }
        if(cartItemId != cartItem.getId()){
            return ResponseUtil.badArgumentValue();
        }

        cartItem = cartService.updateCartItem(cartItem);
        return ResponseUtil.ok(cartItem);
    }

    /**
     * 购物车商品删除
     *
     * @param cartItemId   购物车商品信息
     * @return
     */
    @DeleteMapping("cartItems/{id}")
    public Object delete(@PathVariable("id")Integer cartItemId){
        if(request.getHeader("id") == null)
            return ResponseUtil.unlogin();

        Integer userId = Integer.valueOf(request.getHeader("id"));

        CartItem cartItem = cartService.getCartItemById(cartItemId);

        if(userId != cartItem.getUserId()){
            return ResponseUtil.unauthz();
        }
        if(cartItemId != cartItem.getId()){
            return ResponseUtil.badArgument();
        }

        cartService.deleteCartItem(cartItemId);
        return ResponseUtil.ok();
    }


    /**
     * 购物车商品删除
     *
     * @param cartItems   购物车商品信息
     * @return
     */
    @DeleteMapping("cartItems")
    public Object clearCartItem(@RequestBody List<CartItem> cartItems){
        if(request.getHeader("id") == null)
            return ResponseUtil.unlogin();

        for (CartItem cartItem: cartItems){
            cartService.deleteCartItem(cartItem.getId());
        }
        return ResponseUtil.ok();
    }
}