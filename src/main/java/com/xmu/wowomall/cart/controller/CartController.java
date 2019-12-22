package com.xmu.wowomall.cart.controller;

import com.xmu.wowomall.cart.dao.CartDao;
import com.xmu.wowomall.cart.domain.CartItem;
import com.xmu.wowomall.cart.domain.po.CartItemPo;
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
/**
 * @author 55
 */
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
        return ResponseUtil.ok(cartItem);
    }

    /**
     * 用户购物车信息
     * @return List<CartItem>   用户购物车信息
     */
    @GetMapping("cartItems")
    public Object getCartItems()
    {
        String id= request.getHeader("id");
        if(id == null) {
            return ResponseUtil.unlogin();
        }
        Integer userId = Integer.valueOf(request.getHeader("id"));
        List<CartItem> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseUtil.ok(cartItems);
    }

    /**
     * 加入商品到购物车
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param cartItemPo
     * @return cartItem
     */
    @PostMapping("cartItems")
    public Object addCartItem(@RequestBody CartItemPo cartItemPo) {

        String id= request.getHeader("id");
        if(id == null) {
            return ResponseUtil.unlogin();
        }

        Integer userId = Integer.valueOf(request.getHeader("id"));

        if(cartItemPo.getProductId() == null || cartItemPo.getNumber() == null){ return ResponseUtil.badArgument(); }
        if(cartItemPo.getUserId() != null && !userId.equals(cartItemPo.getUserId())){ return ResponseUtil.unauthz(); }
        if(cartItemPo.getUserId() == null){cartItemPo.setUserId(userId);}

        cartItemPo = cartService.addCartItem(cartItemPo);
        return ResponseUtil.ok(cartItemPo);
    }

    /**
     * 快速购买
     *
     * @param cartItemPo
     * @return cartItem
     */
    @PostMapping("fastAddCartItems")
    public Object fastAdd(@RequestBody CartItemPo cartItemPo) {
        String id= request.getHeader("id");
        if(id == null) {
            return ResponseUtil.unlogin();
        }

        Integer userId = Integer.valueOf(request.getHeader("id"));

        if(cartItemPo.getProductId() == null || cartItemPo.getNumber() == null){ return ResponseUtil.badArgument(); }
        if(cartItemPo.getUserId() != null && !userId.equals(cartItemPo.getUserId())){ return ResponseUtil.unauthz(); }
        if(cartItemPo.getUserId() == null){cartItemPo.setUserId(userId);}

        cartItemPo = cartService.fastAddCartItem(cartItemPo);
        return ResponseUtil.ok(cartItemPo);
    }

    /**
     * 修改购物车商品货品数量
     *
     * @param cartItemPo   购物车商品信息
     * @return cartItem
     */
    @PutMapping("cartItems/{id}")
    public Object update(@PathVariable("id")Integer cartItemId, @RequestBody CartItemPo cartItemPo){
        String id= request.getHeader("id");
        if(id == null) {
            return ResponseUtil.unlogin();
        }
        Integer userId = Integer.valueOf(request.getHeader("id"));

        if(cartItemPo.getProductId() == null || cartItemPo.getNumber() == null){ return ResponseUtil.badArgument(); }
        if(cartItemPo.getUserId() != null && !userId.equals(cartItemPo.getUserId())){ return ResponseUtil.unauthz(); }
        if(cartItemPo.getUserId() == null){cartItemPo.setUserId(userId);}

        cartItemPo = cartService.updateCartItem(cartItemPo);
        return ResponseUtil.ok(cartItemPo);
    }

    /**
     * 购物车商品删除
     *
     * @param cartItemId   购物车商品信息
     * @return
     */
    @DeleteMapping("cartItems/{id}")
    public Object delete(@PathVariable("id")Integer cartItemId){
        String id= request.getHeader("id");
        if(id == null) {
            return ResponseUtil.unlogin();
        }

        Integer userId = Integer.valueOf(request.getHeader("id"));

        CartItem cartItem = cartService.getCartItemById(cartItemId);

        if(!userId .equals(cartItem.getUserId())){
            return ResponseUtil.unauthz();
        }
        if(!cartItemId .equals(cartItem.getId())){
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

        for (CartItem cartItem: cartItems){
            cartService.deleteCartItem(cartItem.getId());
        }

        return ResponseUtil.ok();
    }
}