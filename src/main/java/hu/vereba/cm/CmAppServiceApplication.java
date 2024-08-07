package hu.vereba.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"hu.vereba.cm"})
@EnableJpaRepositories(basePackages = "hu.vereba.cm")
@EntityScan(basePackages ="hu.vereba.cm.database.entity")
public class CmAppServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmAppServiceApplication.class, args);
	}
}
