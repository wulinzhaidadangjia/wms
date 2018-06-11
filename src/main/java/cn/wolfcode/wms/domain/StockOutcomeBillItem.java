package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class StockOutcomeBillItem extends Base {

    private BigDecimal salePrice;

    private BigDecimal number;

    private BigDecimal amount;

    private String remark;

    private Product product;

    private Long bill_id;

}