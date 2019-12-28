package io.renren.common.utils;

/**
 * @author haijun.zhang
 * @date 2019/12/28
 * @time 09:40
 */
public class StrUtils {
    public static String firstToUpperCase(String value){
        if(ObjectTools.isBlank(value)){
            return value;
        }
        String str1=value.substring(0, 1).toUpperCase();

        String str2=value.substring(1).toLowerCase();
        return str1+str2;
    }
}