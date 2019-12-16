package com.xmu.wowomall.cart.domain;

import com.xmu.wowomall.cart.domain.po.ShareRulePo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:分享规则对象
 * @Data:Created in 14:50 2019/12/11
 **/
public class ShareRule extends ShareRulePo {
    public List<Strategy> getStrategyList() {
        return this.strategyList;
    }

    public void setStrategyList(List<Strategy> strategyList) {
        this.strategyList = strategyList;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ShareRule)) return false;
        final ShareRule other = (ShareRule) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$strategyList = this.getStrategyList();
        final Object other$strategyList = other.getStrategyList();
        if (this$strategyList == null ? other$strategyList != null : !this$strategyList.equals(other$strategyList))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ShareRule;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $strategyList = this.getStrategyList();
        result = result * PRIME + ($strategyList == null ? 43 : $strategyList.hashCode());
        return result;
    }

    public String toString() {
        return "ShareRule(strategyList=" + this.getStrategyList() + ")";
    }

    private class Strategy{
        private Integer lowerBound;
        private Integer upperBound;
        private BigDecimal discountRate;

        public Integer getLowerBound() {
            return this.lowerBound;
        }

        public Integer getUpperBound() {
            return this.upperBound;
        }

        public BigDecimal getDiscountRate() {
            return this.discountRate;
        }

        public void setLowerBound(Integer lowerBound) {
            this.lowerBound = lowerBound;
        }

        public void setUpperBound(Integer upperBound) {
            this.upperBound = upperBound;
        }

        public void setDiscountRate(BigDecimal discountRate) {
            this.discountRate = discountRate;
        }
    }
    private List<Strategy> strategyList;
}
