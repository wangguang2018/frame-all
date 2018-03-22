package com.ydd.oms.config.spring;

import com.ydd.framework.core.interceptor.AccessTokenInterceptor;
import com.ydd.oms.interceptor.RequestParamInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring Mvc Configuration
 *
 * @author xingkong1221
 * @since 2017-07-12
 */
@Configuration
public class SpringMvcConfigurer extends WebMvcConfigurerAdapter {

    @Resource
    private AccessTokenInterceptor accessTokenInterceptor;

    @Resource
    private RequestParamInterceptor requestParamInterceptor;


    /**
     * 配置添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册请求缓存拦截器
        registry.addInterceptor(accessTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/druid/*");
        registry.addInterceptor(requestParamInterceptor)
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }



    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
    }
}
