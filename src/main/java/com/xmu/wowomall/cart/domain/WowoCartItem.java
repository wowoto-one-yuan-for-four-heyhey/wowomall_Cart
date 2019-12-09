package com.xmu.wowomall.cart.domain;

import com.xmu.wowomall.cart.entity.CartItem;

/**
 *
 * @author wowoto
 * @date 12/08/2019
 */
public class WowoCartItem extends CartItem {

    private WowoProduct wowoProduct;


    public WowoProduct getWowoProduct() {
        return wowoProduct;
    }

    public void setWowoProduct(WowoProduct wowoProduct) {
        this.wowoProduct = wowoProduct;
    }
}
