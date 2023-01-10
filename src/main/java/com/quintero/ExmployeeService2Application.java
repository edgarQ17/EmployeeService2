package com.quintero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableSwagger2
//@EnableWebMvc
public class ExmployeeService2Application {

	public static void main(String[] args) {
		SpringApplication.run(ExmployeeService2Application.class, args);
	}

}
