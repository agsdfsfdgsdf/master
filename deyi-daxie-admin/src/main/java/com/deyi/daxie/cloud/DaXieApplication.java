package com.deyi.daxie.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author Huang ShuYing
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class DaXieApplication {
    public static void main(String[] args) {
        SpringApplication.run(DaXieApplication.class, args);
    }
}
