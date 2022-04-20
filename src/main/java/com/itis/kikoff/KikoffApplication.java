package com.itis.kikoff;

import com.auth0.jwt.algorithms.Algorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KikoffApplication {

//    @Value("${jwt.secret}")
//    private String secret;
//
//    public static void main(String[] args) {
//        SpringApplication.run(KikoffApplication.class, args);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//
//    @Bean
//    public Algorithm algorithm() {
//        return Algorithm.HMAC256(secret);
//    }

    public static void main(String[] args) {
        SpringApplication.run(KikoffApplication.class, args);
    }
}