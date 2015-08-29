package com.gashli.wshop.utils;

/**
 * @Author gaoshiliang
 * @Date 15/8/26.
 */
public class StringUtils {

    public static boolean isBlank(String str){
        return str==null?true:(str.trim()==""?true:false);
    }

    public static boolean isNotEmpty(String str){
        return !isBlank(str);
    }

    public static boolean isEmpty(String str){
        return isBlank(str);
    }
}
