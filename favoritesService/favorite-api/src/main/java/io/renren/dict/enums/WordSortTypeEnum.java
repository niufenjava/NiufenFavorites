package io.renren.dict.enums;

import io.renren.common.utils.ObjectTools;

/**
 * 单词列表排序类型
 * @author haijun.zhang
 * @date 2019/8/13 16:46
 */
public enum WordSortTypeEnum {

    /**
     *
     */
    DEFAULT("默认排序","name", 1),

    DEGREE("陌生程度","degree", 2),

    QUERY_COUNT("查询次数","query_count", 3),

    MODIFY_TIME("修改时间 ","modify_time", 4);

    /**
     * 构造函数
     *
     * @param name  名称
     * @param value 值
     */
    WordSortTypeEnum(String name,String sql, int value) {
        this.name = name;
        this.value = value;
        this.sql = sql;
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
    private String sql;

    public int getValue() {
        return value;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return value;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
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
        for (WordSortTypeEnum s : WordSortTypeEnum.values()) {
            if (s.getIndex() == index) {
                return s.getName();
            }
        }
        return "";
    }

    /**
     * 通过ID获取排序子弹
     *
     * @param index 下标
     * @return String 名字
     */
    public static String getSqlByIndex(Integer index) {
        if(ObjectTools.isNull(index)){
            return WordSortTypeEnum.DEFAULT.getSql();
        }
        for (WordSortTypeEnum s : WordSortTypeEnum.values()) {
            if (s.getIndex() == index) {
                return s.getSql();
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
    public static WordSortTypeEnum getEnumByIndex(int index) {
        for (WordSortTypeEnum s : WordSortTypeEnum.values()) {
            if (s.getIndex() == index) {
                return s;
            }
        }
        return null;
    }
}
