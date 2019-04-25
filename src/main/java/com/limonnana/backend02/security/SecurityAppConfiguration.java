package com.limonnana.backend02.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


    @Configuration
    @ComponentScan({"com.limonnana"})
    public class SecurityAppConfiguration {

        @Autowired
        CustomURLFilter customURLFilter;


        @Bean
        public FilterRegistrationBean<CustomURLFilter> filterRegistrationBean() {
            FilterRegistrationBean<CustomURLFilter> registrationBean = new FilterRegistrationBean();
            registrationBean.setFilter(customURLFilter);
            registrationBean.addUrlPatterns("/secure/*");
            return registrationBean;
        }
}
