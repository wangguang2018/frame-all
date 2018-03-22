package com.ydd.oms.config.mybatis;

import java.util.List;

public class ParamsUtils  {
    protected static final ThreadLocal<List<SearchParam>> LOCAL_PARAMS = new ThreadLocal<>();


    public static void setLocalParams(List<SearchParam> params) {
        LOCAL_PARAMS.set(params);
    }

    public static List<SearchParam> getLocalParams(){
        return LOCAL_PARAMS.get();
    }

    public static void clearParams() {
        LOCAL_PARAMS.remove();
    }
}
