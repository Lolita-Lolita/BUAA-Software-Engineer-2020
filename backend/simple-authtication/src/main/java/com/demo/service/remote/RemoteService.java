package com.demo.service.remote;

import com.demo.config.SecurityProperties;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

public class RemoteService implements UserService {

    private RemoteConnect connect;
    private SecurityProperties properties;

    @Value("${spring.application.name}")
    private String application;

    public RemoteService(LoadBalancerClient lc, SecurityProperties properties) {
        this.connect = new RemoteConnect(lc);
        this.properties = properties;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String message = "{" + "\"service-id\": \"" + application + "\","
                + "\"username\": \"" + username +
                "\"}";
        RemoteConnect.Address ad = connect.choose(properties.getAuth().getAddress());
        return connect.connect(ad.getIp(), ad.getPort(), Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8)));
    }


}
