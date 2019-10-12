package com.snn.workflow.common;


import java.util.Map;

/**
 * @className Const
 * @Author SNN
 * @Date 2019/9/21 23:47
 **/
public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    public interface Role {
        int ROLE_CUSTOMER = 0;  // 普通用户
        int ROLE_ADMIN = 1;     // 管理员
    }


    public static void getPageData(Integer rowSize, Integer page, long count, Map map) {
        map.put("TotalRows", count);
        map.put("TotalPages", (int) (count/rowSize+1));
        map.put("RowSize", rowSize);
        map.put("CurrentPage", page);
    }
}
