package com.bolsadeideas.springboot.backend.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.annotation.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jAuditing
@EnableNeo4jRepositories("com.bolsadeideas.springboot.backend.apirest.repositories")
public class SpringBootBackendApirestApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}
}
