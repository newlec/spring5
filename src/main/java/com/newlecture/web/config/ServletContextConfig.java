package com.newlecture.web.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

//@Configuration
@ComponentScan(basePackages="com.newlecture.web.controller")
@EnableWebMvc
public class ServletContextConfig implements WebMvcConfigurer {
	
	/* == mvc 설정 ======================================= */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setDefaultCharset(Charset.forName("UTF-8"));
		converter.setWriteAcceptCharset(false);	
		
		converters.add(converter);
		
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
			.addResourceHandler("/resource/**")
			.addResourceLocations("/resource/");
	}
	
	/* == beans 설정 ====================================== */
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions("/WEB-INF/tiles.xml");
		
		return configurer;
	}
	
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		//urlBasedViewResolver.setViewNames("org.springframework.web.servlet.view.tiles3.TilesView");
		urlBasedViewResolver.setViewClass(TilesView.class);
		
		return urlBasedViewResolver;
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		//resolver.setResolveLazily(false);
		resolver.setMaxUploadSize(3145728000L);
		
		return resolver;
	}
	
}
