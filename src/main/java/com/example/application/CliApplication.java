package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CliApplication {
	//

	public static void main(String[] args) throws Exception {
//		SpringApplication.run(CliApplication.class, args);
		var ctx = new AnnotationConfigApplicationContext();
		ctx.scan("com.example");
		ctx.refresh();

		var bean = ctx.getBean(MainRunner.class);

		Configuration config = new Configuration();
		config.setOutputDir("C:\\home\\repos\\frido.github.io");

		bean.run(config);

		ctx.close();
	}

}
