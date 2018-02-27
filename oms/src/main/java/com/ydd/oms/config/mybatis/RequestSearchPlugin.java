package com.ydd.oms.config.mybatis;

import com.ydd.framework.core.common.utils.ReflectUtil;
import com.ydd.framework.core.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

/**
 * 绑定搜索参数
 *
 * @author xingkong1221
 * @since 2017-09-21
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class RequestSearchPlugin implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestSearchPlugin.class);

    /**
     * 搜索前缀
     */
    public static final String SEARCH_PREFIX = "search_";

    /**
     * 分隔符
     */
    public static final String DELIMITER = "_";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 准备参数
        Executor executor = (Executor) invocation.getTarget();
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        BoundSql boundSql;
        //由于逻辑关系，只会进入一次
        if (args.length == 4) {
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
        } else {
            //6 个参数时
            boundSql = (BoundSql) args[5];
        }
        String sql = null;
        Method method = invocation.getMethod();
        sql = buildSearchRequestParameters(boundSql.getSql());

        ReflectUtil.setFieldValue(boundSql, "sql", sql);

        return invocation.proceed();
    }

    /**
     * 构建搜索请求参数
     *
     * @param sql SQL
     * @return SQL
     */
    private String buildSearchRequestParameters(String sql) {
        HttpServletRequest request = null;
        try {
            request = RequestUtils.getRequest();
        }catch (Exception e){}
        if(request == null)
            return sql;
        StringBuilder condition = new StringBuilder(" 1 = 1 ");
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
//        int searchParamSize = 0 ;
//        for (Map.Entry<String, String[]> entry : entrySet) {
//            if (entry.getKey().toLowerCase().startsWith(SEARCH_PREFIX)
//                    && StringUtils.isNotBlank(entry.getValue()[0])) {
//                buildCondition(condition, entry);
//                searchParamSize++;
//            }
//        }
//        if(searchParamSize <= 0)
//            return sql;//没有搜索参数直接返回
        List<SearchParam> searchParams = ParamsUtils.getLocalParams();
        if(searchParams == null || searchParams.size() <= 0)
            return sql;//没有搜索参数直接返回
        else{
            for(SearchParam param : searchParams){
                buildCondition(condition, param);
            }
        }
        String[] sqls = new String[2];
        int index = sql.toLowerCase().lastIndexOf("where");
        sqls[0] = sql.substring(0,index-1);
        sqls[1] = sql.substring(index+5,sql.length());
        return sqls[0] + " WHERE " + condition.toString() + (sqls.length == 2 ? " AND " + sqls[1] : "");
    }


    /**
     * 解析键
     *
     * @param key 键
     * @return 键组
     */
    public String[] parseKey(String key) {
        return key.split(DELIMITER);
    }

    /**
     * 下划线分割
     *
     * @param name 名称
     * @return 名称
     */
    public String splitWithUnderline(String name) {
        String[] sections = StringUtils.splitByCharacterTypeCamelCase(name);
        StringBuilder builder = new StringBuilder("");
        for (String section : sections) {
            builder.append(StringUtils.uncapitalize(section)).append("_");
        }
        return builder.substring(0, builder.length() - 1).replace("_$_", ".");
    }

    /**
     * 构建查询条件
     *
     * @param condition 条件
     * @param param     请求参数
     */
    public void buildCondition(StringBuilder condition, SearchParam param) {
        String[] sections = parseKey(param.getKey());

        String name = splitWithUnderline(sections[2]).replace("$", ".");
        String operator = sections[1];

        if ("eq".equalsIgnoreCase(operator)) {
            condition.append(" and ").append(name).append(" = '").append(param.getValue()).append("'");
        } else if ("neq".equalsIgnoreCase(operator)) {
            condition.append(" and ").append(name).append(" <> '").append(param.getValue()).append("'");
        } else if ("like".equalsIgnoreCase(operator)) {
            condition.append(" and ").append(name).append(" like '%").append(param.getValue()).append("%'");
        }else if("gt".equalsIgnoreCase(operator)){
            condition.append(" and ").append(name).append(" > '").append(param.getValue()).append("'");
        }else if("lt".equalsIgnoreCase(operator)){
            condition.append(" and ").append(name).append(" < '").append(param.getValue()).append("'");
        }

    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
