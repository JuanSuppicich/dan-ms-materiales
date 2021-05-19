package com.durandsuppicich.danmsmateriales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DanMsMaterialesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DanMsMaterialesApplication.class, args);
	}

}
