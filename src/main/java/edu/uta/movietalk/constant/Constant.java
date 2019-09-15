package edu.uta.movietalk.constant;

/**
 * @author housirvip
 */
public class Constant {

    public static final String AUTHORIZATION = "Authorization";

    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = 1;

    public static final int PAGE_NUM_VALUE = 1;
    public static final int PAGE_SIZE_VALUE = 10;
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String ORDER_TYPE = "orderType";
    public static final String ORDER_BY = "orderBy";
    public static final String PARAM = "param";
    public static final String ENABLE = "enable";
    public static final String DISABLE = "disable";
    public static final String ROLE = "role";
    public static final String UID = "uid";

    public static final String PENDING = "pending";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    public static final String LIMITED = "LIMITED";
    public static final String USER = "USER";
    public static final String VIP = "VIP";
    public static final String ADMIN = "ADMIN";
    public static final String ROOT = "ROOT";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ROLE_PREFIX = "ROLE_";

    public static final String SMS_ID = "X-LC-Id";
    public static final String SMS_KEY = "X-LC-Key";
    public static final String CAPTCHA_KEY = "api_key";
    public static final String DING_KEY = "access_token";

    public static final String SMS_OK = "{}";
    public static final String CAPTCHA_OK = "{\"error\":0,\"res\":\"success\"}";
    public static final String DING_OK = "{\"errmsg\":\"ok\",\"errcode\":0}";
}
