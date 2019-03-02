package com.example.demo;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner loadData(AssignmentRepository assignmentRepository) {
//
//        return args -> {
//            log.debug("initializing data...");
//            Assignment assignment = Assignment.builder().assignee("Tom").roomNumber("101").build();
//            Assignment assignment2 = Assignment.builder().assignee("Tom").roomNumber("102").build();
//
//            assignmentRepository.saveAll(Arrays.asList(assignment, assignment2));
//
//            log.debug("saved assignments: {}", assignmentRepository.findAll());
//
//        };
//    }
}

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
            .passwordEncoder(encoder)
            .withUser("user")
            .password(encoder.encode("password"))
            .roles("USER");



    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/**").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic()
            .and().csrf().disable();
    }
}

