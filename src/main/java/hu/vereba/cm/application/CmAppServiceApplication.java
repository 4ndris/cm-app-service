package hu.vereba.cm.application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"hu.vereba.cm"})
@EnableJpaRepositories(basePackages = "hu.vereba.cm")
@EntityScan(basePackages ="hu.vereba.cm.database.entity")
public class CmAppServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmAppServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
