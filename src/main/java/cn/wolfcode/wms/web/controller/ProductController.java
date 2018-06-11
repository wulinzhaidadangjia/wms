package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequestPermission;
import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ServletContext ctx;

    @RequestPermission("商品列表")
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") ProductQueryObject qo, Model model) throws Exception {
        List<Brand> brands = brandService.list();
        model.addAttribute("brands", brands);
        PageResult result = productService.query(qo);
        model.addAttribute("result", result);
        return "/product/list";
    }
    @RequestMapping("selectProductList")
    public String selectProductList(@ModelAttribute("qo") ProductQueryObject qo, Model model) throws Exception {
        List<Brand> brands = brandService.list();
        model.addAttribute("brands", brands);
        PageResult result = productService.query(qo);
        model.addAttribute("result", result);
        return "/product/selectProductList";
    }

    @RequestPermission("商品删除")
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id, String imagePath) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                productService.delete(id, imagePath);
            }
        } catch (Exception e) {
            result.mark("删除失败");
        }

        return new JsonResult();
    }

    @RequestPermission("商品编辑")
    @RequestMapping("input")
    public Object list(Model model, Product entity) {
        List<Brand> brands = brandService.list();
        model.addAttribute("brands", brands);
        if (entity.getId() != null) {
            Product dept = productService.get(entity.getId());
            model.addAttribute("entity", dept);
        }
        return "product/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Model model, Product entity, MultipartFile pic) {
//        if (pic != null && !com.alibaba.druid.util.StringUtils.isEmpty(entity.getImagePath())) {
//            UploadUtil.deleteFile(ctx, entity.getImagePath());
//        }
//        if (pic != null) {
//            String imagePath = UploadUtil.upload(pic, ctx.getRealPath("/upload"));
//            entity.setImagePath(imagePath);
//        }

        productService.saveOrUpdate(entity,pic);
        return new JsonResult();
    }
}
