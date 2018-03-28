package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

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

    public interface ProductListOrderBy{
        Set<String> PRICE_DESC_ASC = Sets.newHashSet("price_desc","price_asc");
    }

    public interface Cart{
        /**
         * 购物车选中状态
         */
        int CHECKED = 1;
        /**
         * 购物车未选中状态
         */
        int UN_CHECKED = 0;

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private int code;
        private String value;


        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }


    }
}
