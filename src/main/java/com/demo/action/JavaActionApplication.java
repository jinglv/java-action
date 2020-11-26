package com.demo.action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author jingLv
 * @date 2020/11/19
 */
@SpringBootApplication
@ServletComponentScan
public class JavaActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaActionApplication.class, args);
    }

}
