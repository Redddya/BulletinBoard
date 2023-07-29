package org.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"org.finalproject.controller", "org.finalproject.util.exception"})
@Import(DBConfigApp.class)
@EnableWebMvc
@EnableScheduling
public class SpringConfig implements WebMvcConfigurer {
}