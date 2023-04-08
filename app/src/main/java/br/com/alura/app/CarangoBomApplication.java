package br.com.alura.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CarangoBomApplication  {
	
	public static void main(String[] args) {
		SpringApplication.run(CarangoBomApplication.class, args);
	}
	
}
