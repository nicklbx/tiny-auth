package com.victor.auth.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.victor.auth.common.BeanCopyUtilCallBack;
import com.victor.auth.model.vo.PageVo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * BeanCopyUtil
 *
 * @Author victor
 * @Date 2022/8/27 17:02
 */
@Slf4j
public class BeanCopyUtil extends BeanUtils {

    /**
     * @param sourceList
     * @param clz
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sourceList, Class<T> clz) {
        if (sourceList == null) {
            return null;
        }
        List<T> list = new ArrayList<>(sourceList.size());
        for (S source : sourceList) {
            T t = null;
            try {
                t = clz.newInstance();
            } catch (Exception e) {
                log.error("clz newInstance failed!");
            }
            copyProperties(source, t);
            list.add(t);
        }
        return list;
    }

    /**
     * <p>
     * Supplier<T> target
     * 举例：
     * UserVo::new
     * 等价于： ()->{new UserVo()}
     * </p>
     *
     * @param sourceList
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sourceList, Supplier<T> target) {
        return copyListProperties(sourceList, target, null);
    }

    /**
     * @param page
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> PageVo<T> copyPageProperties(Page<S> page, Supplier<T> target) {
        if (page == null) {
            return null;
        }
        //分页数据列表
        List<S> sourceList = page.getRecords();
        //返回分页VO
        PageVo<T> pageVo = new PageVo<>();
        //DO列表拷贝VO列表
        List<T> vos = copyListProperties(page.getRecords(), target);
        //拷贝通用分页参数
        copyProperties(page, pageVo);
        pageVo.setList(vos);
        return pageVo;
    }


    /**
     * @param sourceList
     * @param target
     * @param callBack
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sourceList, Supplier<T> target,
            BeanCopyUtilCallBack<S, T> callBack) {
        if (sourceList == null) {
            return null;
        }
        List<T> targetList = sourceList.stream().map((s) -> {
            T t = target.get();
            copyProperties(s, t);
            if (callBack != null) {
                callBack.callBack(s, t);
            }
            return t;
        }).collect(Collectors.toList());
        return targetList;
    }


}
