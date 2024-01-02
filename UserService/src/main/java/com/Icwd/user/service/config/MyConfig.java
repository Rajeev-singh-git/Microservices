package com.Icwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class MyConfig {


	//"http://localhost:8083/ratings/user/20bdb9a5-12dd-49ce-9624-3dd67dc4b37e"

	@Bean
	public RestClient restClient(){
		return RestClient.builder()
				.build();
	}


}
