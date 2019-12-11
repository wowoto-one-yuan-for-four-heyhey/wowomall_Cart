package com.xmu.wowomall.cart.domain;

import com.xmu.wowomall.cart.entity.Product;

/**
 *
 * @author wowoto
 * @date 12/11/2019
 */

public class WowoProduct extends Product {

    WowoGoods wowoGoods;

    public WowoGoods getWowoGoods() {
        return wowoGoods;
    }

    public void setWowoGoods(WowoGoods wowoGoods) {
        this.wowoGoods = wowoGoods;
    }
}
