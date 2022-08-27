package com.victor.auth.model.dto;

import com.victor.auth.common.constraints.Update;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author victor
 * @since 2022-08-27
 */
@Data
@ToString
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "商品ID不为空", groups = {Update.class})
    private Long productId;

    /**
     * 商品名称
     */
    @NotNull(message = "商品价格不为空")
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 图片
     */
    @NotEmpty(message = "商品图片不为空")
    private String pic;

    /**
     * 价格
     */
    @NotNull
    private BigDecimal price;

    /**
     * 状态
     */
    private Integer status;


}
