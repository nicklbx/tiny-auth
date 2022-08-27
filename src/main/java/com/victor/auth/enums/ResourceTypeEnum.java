package com.victor.auth.enums;

import lombok.Getter;

/**
 * ResourceTypeEnum
 *
 * @Author nicklbx
 * @Date 2022/8/27 20:19
 */
@Getter
public enum ResourceTypeEnum {
    MENU(0, "菜单"),
    BUTTON(1, "按钮");

    private int code;
    private String name;

    ResourceTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
