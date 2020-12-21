package fkk.application;

import fkk.enable.annotation.EnableHello;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableRetry
@Configuration
@SpringBootApplication
@EnableAsync
@EnableHello
@EnableRabbit
public class BackCenterApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(BackCenterApplication.class, args);
	}
}
