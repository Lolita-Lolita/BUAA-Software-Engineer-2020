package com.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedList;
import java.util.List;

/**
 * 自定义的配置，交给使用 sdk 的 developer， 用来进行一些功能的定制
 */
@ConfigurationProperties(prefix = "security")
public @Data class SecurityProperties {
    private Filter filter = new Filter();
    private boolean autoScan = true;
    private String[] sysPath = new String[0];
    private String[] whiteList = new String[0];

    public @Data static class Filter {
        private boolean enable = true;
    }
}
