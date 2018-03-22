package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 项目入口
 *
 * @author xingkong1221
 * @since 2017-06-19
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@MapperScan(value = {"com.ydd.oms.mapper","com.bankbuild.api.mapper"})
public class Application {

    /**
     * 启动项目
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
