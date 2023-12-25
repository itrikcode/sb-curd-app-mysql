package com.itsp.curdappmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CurdAppMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurdAppMysqlApplication.class, args);
	}

	public RestTemplate restTemplate(){
		return restTemplate();
	}

}
