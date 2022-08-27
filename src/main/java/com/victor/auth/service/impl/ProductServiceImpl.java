package com.victor.auth.service.impl;

import com.victor.auth.dao.entity.Product;
import com.victor.auth.dao.mapper.ProductMapper;
import com.victor.auth.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
