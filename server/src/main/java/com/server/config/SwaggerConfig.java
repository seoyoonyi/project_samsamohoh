package com.server.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;



@Configuration
public class SwaggerConfig {
	
	@Value("${hostname}")
	private String hostname;
	
	@Bean
	public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
		Info info = new Info().title("삼삼오오 RESTFul API").version(appVersion).description("삼삼오오 웹 애플리케이션 API")
				.termsOfService("http://swagger.io/terms/")
				.contact(new Contact().name("이재원").email("koma1416@naver.com")).license(new License()
						.name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));
		
		
		Server httpServer = new Server();
		httpServer.setUrl("http://"+hostname);
		
		Server httpsServer = new Server();
		httpsServer.setUrl("https://"+hostname);
		
		return new OpenAPI().servers(Arrays.asList(httpServer,httpsServer)).components(new Components()).info(info);
	}

}
