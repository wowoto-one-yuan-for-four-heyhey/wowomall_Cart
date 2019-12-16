package com.xmu.wowomall.cart.domain;

import com.xmu.wowomall.cart.domain.po.GoodsPo;
import com.xmu.wowomall.cart.domain.po.ProductPo;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:产品对象
 * @Data:Created in 14:50 2019/12/11
 **/
public class Product extends ProductPo {
    private GoodsPo goodsPo;

    public GoodsPo getGoodsPo() {
        return this.goodsPo;
    }

    public void setGoodsPo(GoodsPo goodsPo) {
        this.goodsPo = goodsPo;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$goodsPo = this.getGoodsPo();
        final Object other$goodsPo = other.getGoodsPo();
        if (this$goodsPo == null ? other$goodsPo != null : !this$goodsPo.equals(other$goodsPo)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $goodsPo = this.getGoodsPo();
        result = result * PRIME + ($goodsPo == null ? 43 : $goodsPo.hashCode());
        return result;
    }

    public String toString() {
        return "Product(goodsPo=" + this.getGoodsPo() + ")";
    }
}
