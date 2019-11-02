package com.learn.spring;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

@EnableTransactionManagement
@EnableJpaRepositories("com.learn.spring.domain")
@SpringBootApplication
public class EmployeeApplication {
	public static void main(String[] args) {
		//startH2Server();
		SpringApplication.run(EmployeeApplication.class, args);
	}
	private static void startH2Server() {
		try {
			Server h2Server = Server.createTcpServer().start();
			if (h2Server.isRunning(true)) {
				System.out.println("H2 server was started and is running.>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			} else {
				throw new RuntimeException("Could not start H2 server.>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to start H2 server: ", e);
		}
	}
}
