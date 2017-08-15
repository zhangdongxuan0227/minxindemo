package com.minxin.util;

import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017/7/19.
 */
public class ResourceUtil {

    // 从properties 文件中，读取指定字符
    public static String getResource(String baseName, String key){
        ResourceBundle rb = ResourceBundle.getBundle(baseName);
        return rb.getString(key);
    }
}
