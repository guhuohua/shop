package com.wxapp.shopapp.constant;

public interface PageConstant {
    String PAGE = "page";
    String SIZE = "limit";
    String NAME = "username";
    String TEL = "tel";
    String USER_ID = "userId";
    String TITLE = "title";
    String KEYWORD = "keyword";

    String STRING_LIKE_MODEL = "%%";

    String ORDER_STATUS_DEFAULT = "1,2,3,4,5";
    String ORDER_STATUS_ALL = "4";

    String PAGE_VALUE = "1";
    String SIZE_VALUE = "20";
    String EMPTY = "";

    default String getFuzzyQuery(String string) {
        return "%" + string + "%";
    }

    default String getFuzzyQuery(int num) {
        return "%" + String.valueOf(num) + "%";
    }
}
