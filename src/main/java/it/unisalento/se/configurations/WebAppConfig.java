package it.unisalento.se.configurations;

import it.unisalento.se.common.CommonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"it.unisalento.se"})
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        return CommonUtils.getCommonUtils().getCommonResolver();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST");
    }

}
