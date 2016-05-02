package org.diveintojee.poc.standardstack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = {"org.diveintojee.poc.standardstack.steps"})
public class StepsConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
