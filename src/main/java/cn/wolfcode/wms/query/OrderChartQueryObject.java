package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class OrderChartQueryObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Long brandId = -1L;
    private Long supplierId = -1L;
    private String keyword;
    private String groupType = "e.name";
    public static Map<String, String> groupByTypeMap = new LinkedHashMap<>();

    static {
        groupByTypeMap.put("e.name", "订货员");
        groupByTypeMap.put("p.name", "商品名称");
        groupByTypeMap.put("p.brandName", "商品品牌");
        groupByTypeMap.put("s.name", "供应商");
        groupByTypeMap.put("DATE_FORMAT(bill.vdate,'%Y-%m')", "订货月份");
        groupByTypeMap.put("DATE_FORMAT(bill.vdate,'%Y-%m-%d')", "订货日期");
    }
}
