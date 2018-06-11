package cn.wolfcode.wms.domain;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;

@Setter
@Getter
@ToString
public class Product extends Base {

    private String name;

    private String sn;

    private BigDecimal costPrice;

    private BigDecimal salePrice;

    private String imagePath;

    private String intro;

    private Long brand_id;

    private String brandName;


    public String getSmallImagePath() {
        if (StringUtils.isEmpty(imagePath)) {
            return "";
        } else {
            int index = imagePath.indexOf(".");
            return imagePath.substring(0, index) + "_small" + imagePath.substring(index);
        }
    }

    public String getProductInfo() throws Exception {
        HashMap<Object, Object> info = new HashMap<>();
        info.put("id", getId());
        info.put("name", name);
        info.put("brand", brandName);
        info.put("costPrice", costPrice);
        info.put("salePrice", salePrice);
        ObjectMapper json = new ObjectMapper();
        return json.writeValueAsString(info);
    }
}