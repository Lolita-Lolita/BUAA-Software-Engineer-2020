package com.example.user.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rbac")
@Data
public class Rbac {
    private Integer port = 8081;
}
