package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductQueryObject extends QueryObject {
    private String keyword;
    private Long brand_id;
}
