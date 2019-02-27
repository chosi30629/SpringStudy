package me.chosi.springmvc01;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 스프링부트 자동 설정 + 추가 설정(커스터마이징)
 */
@Configuration
/**
 * 이거까지 붙이면 스프링부트 자동 설정 파괴
 */
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

}
