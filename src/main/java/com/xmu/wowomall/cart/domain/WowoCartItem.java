package com.xmu.wowomall.cart.domain;

import com.xmu.wowomall.cart.entity.CartItem;
import org.apache.ibatis.type.Alias;

/**
 *
 * @author wowoto
 * @date 12/08/2019
 */
@Alias("WowoCartItem")
public class WowoCartItem extends CartItem {

    private WowoProduct wowoProduct;

    public WowoProduct getWowoProduct() {
        return wowoProduct;
    }

    public void setWowoProduct(WowoProduct wowoProduct) {
        this.wowoProduct = wowoProduct;
    }
}
