package com.server2.demo.rabbit;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.rabbitmq")
@AllArgsConstructor
@Getter
public class RabbitMqProperties {
    private String host;
    private int port;
    private String username;
    private String password;

    @PostConstruct
    void test(){
        System.out.println("host = " + host);
    }
}
