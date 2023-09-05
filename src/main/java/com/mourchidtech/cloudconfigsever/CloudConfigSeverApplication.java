package com.mourchidtech.cloudconfigsever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableConfigServer
@RestController
public class CloudConfigSeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudConfigSeverApplication.class, args);
	}

}
