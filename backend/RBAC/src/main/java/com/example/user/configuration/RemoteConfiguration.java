package com.example.user.configuration;

import com.demo.config.SecurityProperties;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({SecurityProperties.class, Rbac.class})
@Configuration
public class RemoteConfiguration {

    private @Autowired SecurityProperties properties;
    private @Autowired Rbac port;

    public @Bean RemoteServer remoteServer(@Autowired UserRepository repository) {
        return new RemoteServer(repository, port.getPort(), properties);
    }
}
