package com.ydd.oms.interceptor;

import com.ydd.oms.config.mybatis.ParamsUtils;
import com.ydd.oms.config.mybatis.SearchParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RequestParamInterceptor extends HandlerInterceptorAdapter {

    /**
     * 搜索前缀
     */
    public static final String SEARCH_PREFIX = "search_";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<SearchParam> searchParams = new ArrayList<>();
        //将搜索参数放入attr
        Map<String, String[]> paramMap = request.getParameterMap();
        for(String key : paramMap.keySet()){
            if(key.startsWith(SEARCH_PREFIX)){
                if(StringUtils.isNotBlank(paramMap.get(key)[0]))
                    searchParams.add(new SearchParam(key,paramMap.get(key)[0]));
            }
        }
        ParamsUtils.setLocalParams(searchParams);
        return super.preHandle(request, response, handler);
    }
}
