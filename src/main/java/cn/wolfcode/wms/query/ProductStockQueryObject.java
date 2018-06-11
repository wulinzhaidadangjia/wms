package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductStockQueryObject extends QueryObject {
    private String keyword;
    private Long brandId;
    private Long depotId;
    private Integer warnNum;
}
