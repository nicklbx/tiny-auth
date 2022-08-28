package com.victor.auth.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
    @JsonProperty("total")
    private long total;
    /**
     * 当前页数据条数
     */
    @JsonProperty("pageSize")
    private long size;
    /**
     * 当前页数
     */
    @JsonProperty("pageNo")
    private long current;
    /**
     * 记录列表
     */
    @JsonProperty("list")
    private List<T> list;

}
