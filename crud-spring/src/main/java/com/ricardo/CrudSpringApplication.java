package com.ricardo;

import com.ricardo.model.Course;
import com.ricardo.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	//A anotação 'Bean' diz pro spring gerenciar todo o ciclo de vida deste fluxo
	//Este comando 'CommandLineRunner' coloca este comando pra ser executado assim que esta aplicação subir
	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository) {

		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory("front-end");

			courseRepository.save(c);
		};

	}

}
