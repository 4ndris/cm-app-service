package hu.vereba.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"hu.vereba.cm"})
@EnableMongoRepositories(basePackages = "hu.vereba.cm.database.repository")
public class CmAppServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmAppServiceApplication.class, args);
	}
}