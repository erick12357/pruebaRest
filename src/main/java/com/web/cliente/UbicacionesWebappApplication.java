package com.web.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.test.mongoLib.MongoLibConfig;

@SpringBootApplication
@Import(MongoLibConfig.class)
public class UbicacionesWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(UbicacionesWebappApplication.class, args);
	}

}
