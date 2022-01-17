package com.pshc.isms;

import com.pshc.isms.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
public class IsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsmsApplication.class, args);
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Primary
	@Bean
	public RestTemplate getCustomRestTemplate() {
		RestTemplate template = new RestTemplate();
		List interceptors = template.getInterceptors();
		if (interceptors == null) {
			template.setInterceptors(Collections.singletonList(
					new UserContextInterceptor()
			));
		} else {
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}
		return template;
	}

}
