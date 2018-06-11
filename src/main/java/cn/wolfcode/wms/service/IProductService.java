package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductQueryObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    void saveOrUpdate(Product entity, MultipartFile pic);

    void delete(Long id, String imagePath);

    Product get(Long id);

    List<Product> list();

    PageResult query(ProductQueryObject qo);
}
