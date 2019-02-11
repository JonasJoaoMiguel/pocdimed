package br.com.jonascruz.pocdimed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("br.com.jonascruz.pocdimed.repository")
public class PocDimedApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocDimedApplication.class, args);
	}

}

