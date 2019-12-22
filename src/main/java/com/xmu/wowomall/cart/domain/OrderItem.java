package com.xmu.wowomall.cart.domain;

import com.xmu.wowomall.cart.domain.po.OrderItemPo;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:订单明细对象
 * @Data:Created in 14:50 2019/12/11
 **/
public class OrderItem extends OrderItemPo {
    private Product product;

    public Product getProduct() {
        return this.product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OrderItem newItem = (OrderItem) super.clone();
        newItem.setId(null);
        return newItem;
    }

    @Override
    public boolean equals(final Object o) {

        return true;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof OrderItem;
    }

    @Override
    public int hashCode() {

        return 0;
    }

    @Override
    public String toString() {
        return "OrderItem(product=" + this.getProduct() + ")";
    }

    public enum StatusCode{
        /**
         * g
         */
        NOT_PAYED(0),
        NOT_SHIPPED(1),
        NOT_CONFIRMED(2),
        CONFIRMED(3),
        FINISHED(4),
        APPLY_RETURN(5),
        RETURN_SUCCESS(6),
        APPLY_EXCHANGE(7),
        EXCHANGE_SUCCESS(8),
        PRESALE(9);

        private final int value;

        StatusCode(int value) { this.value = value; }

        public int getValue() { return value; }
    }
    public OrderItem(){
        super();
    }

    public OrderItem(CartItem cartItem){
        this.setNumber(cartItem.getNumber());
        Product product = cartItem.getProduct();
        this.setProduct(product);
        this.setProductId(product.getId());
        this.setPrice(product.getPrice());
        this.setDealPrice(this.getPrice());
    }
}
