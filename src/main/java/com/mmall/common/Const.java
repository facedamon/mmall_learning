package com.mmall.common;

/**
 *@author facedamon
 *@description 常量工具类
 *@create 2018/3/6
 */
public class Const {
    /**
     *@author facedamon
     *@description 当前用户识别字符
     *@param 当前用户
     *@create 2018/3/6
     */
    public static final String CURRENT_USER = "currentUser";

    public static final String USERNAME = "username";

    public static final String EMAIl = "email";


    /**
     *@author facedamon
     *@description 角色采用接口分组
     *@create 2018/3/6
     */
    public interface Role{
        int ROLE__CUSTOMER = 0;
        int ROLE_ADMIN = 1;
    }
}
