package io.renren.dict.enums;

import io.renren.common.utils.ObjectTools;

/**
 * 单词词性枚举
 * @author haijun.zhang
 * @date 2019/8/13 16:46
 */
public enum WordDefTypeEnum {

    /**
     *
     */
    NOUN("n.", 1),

    VERB("v.", 2),

    ADJECTIVE("adj. ", 3),

    ADVERB("adv. ", 4),

    PREPOSITION("prep. ", 5),

    CONJUNCTION("conj. ", 6),

    PHRASE("phrase. ", 7);

    /**
     * 构造函数
     *
     * @param name  名称
     * @param value 值
     */
    WordDefTypeEnum(String name, int value) {
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
        for (WordDefTypeEnum s : WordDefTypeEnum.values()) {
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
    public static WordDefTypeEnum getEnumByIndex(int index) {
        for (WordDefTypeEnum s : WordDefTypeEnum.values()) {
            if (s.getIndex() == index) {
                return s;
            }
        }
        return null;
    }
}
