package io.rachidassouani.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}


	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
/*
	@Bean
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}

*/
}
