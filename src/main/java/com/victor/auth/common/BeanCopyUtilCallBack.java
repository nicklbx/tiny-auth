package com.victor.auth.common;

/**
 * BeanCopyUtilCallBack
 *
 * @Author victor
 * @Date 2022/8/27 17:04
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack<S, T> {

    /**
     * 定义默认回调方法
     *
     * @param s
     * @param t
     */
    void callBack(S s, T t);
}
