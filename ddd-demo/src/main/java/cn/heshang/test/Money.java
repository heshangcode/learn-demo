package cn.heshang.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/15 05:47
 * version: 1.0
 */
public class Money {

    private Long value;
    private Currency currency;

    private Money(Long value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Money of(long value, Currency currency) {
        return new Money(value, currency);
    }

    public static Money addTwoMoney(Money money1, Money money2) {
        // 这里引入货币计算规则，把 2 个货币全部转成人民币，然后进行计算，再 new Money(value，人民币)返回
        return new Money(0L, Currency.CNY);
    }

    @Getter
    @AllArgsConstructor
    public enum Currency {
        CNY("CNY");
        //省略其他货币...
        private String name;
    }


}
