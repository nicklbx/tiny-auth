package com.victor.auth.model.vo;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

/**
 * PageVo
 *
 * @Author victor
 * @Date 2022/8/27 16:25
 */
@Data
@ToString
public class PageVo<T> {

    /**
     * 总条数
     */
    private long total;
    /**
     * 总页数
     */
    private long totalPages;
    /**
     * 当前页数
     */
    private long currentPage;
    /**
     * 记录列表
     */
    private T list;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
