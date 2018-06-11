package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ServletContext ctx;

    @Override
    public void saveOrUpdate(Product entity, MultipartFile pic) {
        //修改图片
        if (pic != null && !com.alibaba.druid.util.StringUtils.isEmpty(entity.getImagePath())) {
            UploadUtil.deleteFile(ctx, entity.getImagePath());
        }
        //上传图片
        if (pic != null) {
            String imagePath = UploadUtil.upload(pic, ctx.getRealPath("/upload"));
            entity.setImagePath(imagePath);
        }
        //设置品牌名称
        Brand brand = brandMapper.selectByPrimaryKey(entity.getBrand_id());
        entity.setBrandName(brand.getName());
        if (entity.getId() != null) {
            productMapper.updateByPrimaryKey(entity);
        } else {
            productMapper.insert(entity);
        }
    }

    @Override
    public void delete(Long id, String imagePath) {
        if (id != null) {
            productMapper.deleteByPrimaryKey(id);
            UploadUtil.deleteFile(ctx, imagePath);
        }
    }

    @Override
    public Product get(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> list() {
        return productMapper.selectAll();
    }

    @Override
    public PageResult query(ProductQueryObject qo) {
        int totalCount = productMapper.query4Count(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_RESULT;
        }
        List<Product> list = productMapper.query4List(qo);

        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, list);
    }
}

