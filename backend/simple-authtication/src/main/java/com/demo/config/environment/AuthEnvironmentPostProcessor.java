package com.demo.config.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import java.util.*;

/**
 * 进行配置的初始化和强制修改
 */
public class AuthEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if ("bootstrap".equals(environment.getProperty("spring.application.name", "bootstrap"))) {
            return;
        }
        Integer value = environment.getProperty("server.servlet.session.timeout", Integer.class);
        boolean enable = environment.getProperty("security.filter.enable", boolean.class, true);
        boolean autoScan = environment.getProperty("security.auto-scan", boolean.class, true);
        if (enable) {
            if (Objects.isNull(value)) {
                addProperty(environment, "server.servlet.session.timeout", 150);
            }
            if (autoScan) {
                addProperty(environment, "security.scan-entity", application.getMainApplicationClass().getPackage().getName());
                addProperty(environment, "security.scan-repositories", application.getMainApplicationClass().getPackage().getName());
            }
        } else {
            addProperty(environment, "spring.autoconfigure.exclude", "org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration");
        }
    }

    private void addProperty(ConfigurableEnvironment environment, String v, Object value) {
        Map<String, Object> map;
        if (environment.getPropertySources().contains("default_property_source")) {
            map = ((MapPropertySource) environment.getPropertySources().get("default_property_source")).getSource();
        } else {
            map = new HashMap<>();
            environment.getPropertySources().addFirst(new MapPropertySource("default_property_source", map));
        }
        map.put(v, value);
        System.out.println("add default property " + v + ": " + value);
    }
}
