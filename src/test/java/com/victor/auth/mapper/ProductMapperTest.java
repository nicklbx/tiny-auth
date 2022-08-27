package com.victor.auth.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.victor.auth.BaseTest;
import com.victor.auth.dao.entity.Product;
import com.victor.auth.dao.mapper.ProductMapper;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;

/**
 * ProductMapperTest
 *
 * @Author victor
 * @Date 2022/8/26 23:25
 */
public class ProductMapperTest extends BaseTest {

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testInsert() {
        Product product = new Product();
        product.setName("小米手机");
        System.out.println(productMapper.insert(product));

    }

    @Test
    public void testUpdate() {
        Product product = new Product();
        product.setName("华为手机");
//        product.setProductId(6L);
        Product product1 = productMapper.selectOne(new QueryWrapper<Product>().eq("name", "小米手机"));

        System.out.println(productMapper.update(product
                , new QueryWrapper<Product>(product1)));
    }

    @Test
    public void testDelete() {
        productMapper.delete(new QueryWrapper<Product>().eq("product_id", "6"));
    }


    @Test
    public void testPage() {
        Page<Product> page = new Page<>(3, 5);
        Page<Product> productPage = productMapper.selectPage(page, null);
        List<Product> records = productPage.getRecords();

        System.out.println(productPage.getRecords());
        System.out.println(productPage.getCurrent());
        System.out.println(productPage.getTotal());
        System.out.println(productPage.getSize());
        System.out.println(productPage.hasNext());

    }


}
