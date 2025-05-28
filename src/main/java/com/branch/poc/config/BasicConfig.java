package com.branch.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/*
  Track time:
  *** 5/26/25 - About 1-2 hours tonight
  *** 5/27/25 - About 30 minutes at lunch
  *** 5/27/25 - After work ... start around 3:15 pm...sort of like 30-60% time spent .. - 7:15??
*/

@Configuration
public class BasicConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
