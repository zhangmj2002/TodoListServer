package org.example.todolistserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(basePackages = "org.example.todolistserver.mapper")
@SpringBootApplication
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class TodoListServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListServerApplication.class, args);
	}

}
