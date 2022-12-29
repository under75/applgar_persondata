package ru.tfoms.applgar.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.tfoms.applgar.interceptor.AuthorizedUserFilter;

@Configuration
public class authFilterConfig {
	@Bean
	public FilterRegistrationBean<Filter> authFilterRegistration() {

		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
		registration.setFilter(authFilter());
		registration.addUrlPatterns("/appl/*", "/pers/*");
		registration.setName("authFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean(name = "authFilter")
	public Filter authFilter() {
		return new AuthorizedUserFilter();
	}
}
