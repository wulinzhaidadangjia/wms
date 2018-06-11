package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class ProductStock extends Base {

    private BigDecimal price;

    private BigDecimal storeNumber;

    private BigDecimal amount;

    private Product product;

    private Depot depot;

}