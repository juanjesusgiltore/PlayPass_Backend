package com.playpass.backend.activity.infraestructure.conf;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ActivityConf {


    @Bean
    CommandLineRunner init(){
        return args -> {

        };
    }
}
