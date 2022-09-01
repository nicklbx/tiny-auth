package com.victor.auth.controller.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.victor.auth.common.constraints.Update;
import com.victor.auth.common.utils.R;
import com.victor.auth.common.vo.ApiRespVo;
import com.victor.auth.dao.entity.Product;
import com.victor.auth.model.dto.ProductDTO;
import com.victor.auth.model.vo.PageVo;
import com.victor.auth.model.vo.ProductVo;
import com.victor.auth.service.ProductService;
import com.victor.auth.utils.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/product")
@Validated
@CrossOrigin
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * 查看商品列表
     *
     * @return
     */
    @ApiOperation(value = "查看所有商品")
    @GetMapping("/listProduct")
    public ApiRespVo listProduct(@RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "100") int pageSize,
            ProductDTO productDTO) {
        Product product = new Product();
        BeanCopyUtil.copyProperties(productDTO, product);
        Page<Product> productPage = new Page<>(pageNo, pageSize);
        Page<Product> page = productService.page(productPage, new QueryWrapper<Product>(product));
        //分页数据封装
        PageVo<ProductVo> pageVo = BeanCopyUtil.copyPageProperties(page, ProductVo::new);
        return R.ok(pageVo);
    }

    /**
     * 添加商品
     *
     * @param productDTO
     * @return
     */
    @ApiOperation("添加商品")
    @PostMapping("/add")
    public ApiRespVo createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = new Product();
        BeanCopyUtil.copyProperties(productDTO, product);
        product.setProductId(null);
        productService.save(product);
        return R.ok(product);
    }


    /**
     * 修改商品
     *
     * @param productDTO
     * @return
     */
    @ApiOperation("修改商品")
    @PostMapping("/update")
    public ApiRespVo updateProduct(@Validated(Update.class) @RequestBody ProductDTO productDTO) {
        Product product = new Product();
        BeanCopyUtil.copyProperties(productDTO, product);
        productService.updateById(product);
        return R.ok(product);
    }

    /**
     * 删除商品
     *
     * @param productId
     * @return
     */
    @ApiOperation("删除商品")
    @PostMapping("/delete/{productId}")
    public ApiRespVo deleteProduct(@NotNull @PathVariable Long productId) {
        productService.removeById(productId);
        return R.ok();
    }


}
