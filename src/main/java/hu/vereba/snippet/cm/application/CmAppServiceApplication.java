package hu.vereba.snippet.cm.application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"hu.vereba.snippet.cm"})
@EnableJpaRepositories(basePackages = "hu.vereba.snippet.cm")
@EntityScan(basePackages ="hu.vereba.snippet.cm.database.entity")
@EnableSwagger2
public class CmAppServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmAppServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
