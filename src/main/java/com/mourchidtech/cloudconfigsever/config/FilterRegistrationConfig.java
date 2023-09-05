package com.mourchidtech.cloudconfigsever.config;


import com.mourchidtech.cloudconfigsever.filter.AppKeyFilter;
import com.mourchidtech.cloudconfigsever.filter.IpAddressFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationConfig {

    @Bean
    public FilterRegistrationBean<AppKeyFilter> filterRegistrationBean() {
        FilterRegistrationBean<AppKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AppKeyFilter());
        registrationBean.addUrlPatterns("/admin/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<IpAddressFilter> filterRegistrationBeanIp() {
        FilterRegistrationBean<IpAddressFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new IpAddressFilter());
        registrationBean.addUrlPatterns("/admin/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}