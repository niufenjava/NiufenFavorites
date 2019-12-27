package io.renren.common.enums;

import io.renren.common.utils.ObjectTools;

/**
 * 成功或失败的通用枚举
 * @author haijun.zhang
 * @date 2019/8/13 16:46
 */
public enum YesOrNoEnum {
    /**
     * 是、通过
     */
    YES("是", Boolean.TRUE ,1),
    /**
     * 不是、不通过
     */
    NO("否 ", Boolean.FALSE ,0);

    /**
     * 构造函数
     *
     * @param name  名称
     * @param value 值
     */
    YesOrNoEnum(String name,Boolean flag, int value) {
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
    /**
     * value
     */
    private Boolean flag;

    public int getValue() {
        return value;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return value;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * 通过ID获取名字
     *
     * @param index 下标
     * @return String 名字
     */
    public static Boolean getFlag(Integer index) {
        if(ObjectTools.isNull(index)){
            return Boolean.FALSE;
        }
        if(index == 1){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 通过ID获取名字
     *
     * @param flag 下标
     * @return String 名字
     */
    public static Integer getIndex(Boolean flag) {
        if(ObjectTools.isNull(flag)){
            return NO.getIndex();
        }
        if(flag){
            return YES.getIndex();
        }
        return NO.getIndex();
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
        for (YesOrNoEnum s : YesOrNoEnum.values()) {
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
    public static YesOrNoEnum getEnumByIndex(int index) {
        for (YesOrNoEnum s : YesOrNoEnum.values()) {
            if (s.getIndex() == index) {
                return s;
            }
        }
        return null;
    }
}
