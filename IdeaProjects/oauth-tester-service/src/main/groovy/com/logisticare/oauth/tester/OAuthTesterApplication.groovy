package com.logisticare.oauth.tester

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class OAuthTesterApplication {
    static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        SpringApplication.run(OAuthTesterApplication.class, args)
    }

    @Bean
    static RestTemplate getClient() {
        return new RestTemplateBuilder().build()
    }
}