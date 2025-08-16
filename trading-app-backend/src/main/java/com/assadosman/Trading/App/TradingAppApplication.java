package com.assadosman.Trading.App;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TradingAppApplication {

	public static void main(String[] args) {
		// Loading Dontenv ensures that we can use our env file to access the database
		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(TradingAppApplication.class, args);
	}
}
