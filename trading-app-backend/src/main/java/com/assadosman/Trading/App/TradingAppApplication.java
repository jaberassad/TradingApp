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

	@Configuration
	public static class WebConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			// Allow CORS for all paths and allow requests from specific origins
			registry.addMapping("/**")
					.allowedOrigins("http://yourfrontend.com")  // Replace with your frontend's URL
					.allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow these HTTP methods
					.allowedHeaders("*")  // Allow all headers
					.allowCredentials(true);  // Allow sending of credentials (cookies, etc.)
		}
	}
}
