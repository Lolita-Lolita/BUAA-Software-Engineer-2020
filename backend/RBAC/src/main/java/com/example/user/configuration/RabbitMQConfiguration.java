package com.example.user.configuration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RabbitMQConfiguration {

    String USER_OUTPUT= "user";

    @Output(RabbitMQConfiguration.USER_OUTPUT)
    MessageChannel send();

}
