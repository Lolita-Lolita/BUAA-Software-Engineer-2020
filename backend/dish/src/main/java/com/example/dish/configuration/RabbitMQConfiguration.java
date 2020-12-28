package com.example.dish.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RabbitMQConfiguration {

    String USER_INPUT = "user";

    @Input(RabbitMQConfiguration.USER_INPUT)
    SubscribableChannel userMessage();
}
