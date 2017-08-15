package com.minxin.common;

import com.minxin.util.ResourceUtil;

/**
 * Created by Administrator on 2017/7/19.
 */
public class Constant {

    // 获取项目链接
    public static final String HOUTAI_BASE_URL = getResource("houtai_base_url");

    private static String getResource(String key){
        String baseName = "constant";
        return ResourceUtil.getResource(baseName, key);
    }
}
