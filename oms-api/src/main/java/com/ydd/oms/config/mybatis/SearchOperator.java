package com.ydd.oms.config.mybatis;

/**
 * 搜索操作符
 *
 * @author xingkong1221
 * @since 2017-10-09
 */
public enum SearchOperator {

    EQ("eq", "="),          // 等于
    NEQ("neq", "<>"),       // 不等于
    LIKE("like", "like"),   // 模糊查询
    ;
    public String name;
    public String symbol;

    SearchOperator(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
