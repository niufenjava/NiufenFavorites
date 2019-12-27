package io.renren.common.enums;

import io.renren.common.utils.ObjectTools;

/**
 * 成功或失败的通用枚举
 * @author haijun.zhang
 * @date 2019/8/13 16:46
 */
public enum SuccessOrFailEnum {
    /**
     * 是、通过
     */
    SUCCESS("成功", 1),
    /**
     * 不是、不通过
     */
    FAIL("失败 ", 0);

    /**
     * 构造函数
     *
     * @param name  名称
     * @param value 值
     */
    SuccessOrFailEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 名称
     */
    private String name;
    /**
     * value
     */
    private int value;

    public int getValue() {
        return value;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return value;
    }

    /**
     * 通过ID获取名字
     *
     * @param index 下标
     * @return String 名字
     */
    public static String getNameByIndex(Integer index) {
        if(ObjectTools.isNull(index)){
            return "";
        }
        for (SuccessOrFailEnum s : SuccessOrFailEnum.values()) {
            if (s.getIndex() == index) {
                return s.getName();
            }
        }
        return "";
    }

    /**
     * 通过ID获取枚举
     *
     * @param index 下标
     * @return OperateFromEnum 枚举
     */
    public static SuccessOrFailEnum getEnumByIndex(int index) {
        for (SuccessOrFailEnum s : SuccessOrFailEnum.values()) {
            if (s.getIndex() == index) {
                return s;
            }
        }
        return null;
    }
}
