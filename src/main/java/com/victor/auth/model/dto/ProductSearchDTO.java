package com.victor.auth.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * ProductSearchDTO
 *
 * @Author victor
 * @Date 2022/9/1 22:02
 */
@Data
@ToString
public class ProductSearchDTO {

    private Long productId;

    /**
     * 商品名称
     */
    private String name;


}
