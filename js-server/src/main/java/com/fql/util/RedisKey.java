package com.fql.util;

/**
 * @author Qian
 * @date 2022-10-26
 * 用于设计redis key前缀枚举 ，通过 databaseName+tableName+用户id进行实现key设计
 *
 */
public enum RedisKey {
    /**
     * 用户key
     */
    USER_KEY("t_user"),
    /**
     *学员key
     */
    STUDENT_KEY("t_student"),
    /**
     *产品key
     */
    PRODUCT_KEY("t_product"),
    /**
     *
     */
    OUR_TEAM_KEY("t_our_team"),
    /**
     *
     */
    NEWS_KEY("t_news"),
    /**
     *
     */
    NAV_KEY("t_nav"),
    /**
     *
     */
    CUSTOMER_KEY("t_customer"),
    /**
     *
     */
    CUSTOMER_FAIL_MES("customer_fail_mes"),
    CUSTOMER_EMAIL("customer_email"),
    /**
     *
     */
    COMPANY_PROFILE_KEY("t_company_profile"),
    /**
     *
     */
    COMPANY_CONTACT_KEY("t_company_contact");


    private String prefixKey;
    private final String tableName ;
    RedisKey(String tableName){
        this.tableName = tableName;
    }
    private void join(){
        String databaseName = "spring_boot_demo";
        StringBuilder str = new StringBuilder(databaseName);
        this.prefixKey = str.append(":").append(tableName).toString();
    }
    public String getKey(){
        join();
        return prefixKey;
    }
}
