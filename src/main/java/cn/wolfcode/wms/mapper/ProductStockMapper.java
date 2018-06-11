package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {

    int insert(ProductStock entity);

    int updateByPrimaryKey(ProductStock entity);

    ProductStock selectByPIdAndDepotId(@Param("productId") Long productId, @Param("depotId") Long depotId);

    int query4Count(ProductStockQueryObject qo);

    List<ProductStock> query4List(ProductStockQueryObject qo);
}