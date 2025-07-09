package br.com.train.springkeycloack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpConfig {

    @Bean
    public HttpHeaders HttpHeaders() {
        return new HttpHeaders();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
