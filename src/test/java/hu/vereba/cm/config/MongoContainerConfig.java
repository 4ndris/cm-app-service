package hu.vereba.cm.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration
public class MongoContainerConfig {

    private static final MongoDBContainer MONGO_CONTAINER;

    static {
        MONGO_CONTAINER = new MongoDBContainer("mongo:8"); // Specify the MongoDB version
        MONGO_CONTAINER.start();
    }

    @Bean
    public MongoDBContainer mongoContainer() {
        return MONGO_CONTAINER;
    }

    @DynamicPropertySource
    static void registerMongoProperties(DynamicPropertyRegistry registry) {
        // Set Spring Data MongoDB properties based on the container's connection info
        registry.add("spring.data.mongodb.uri", MONGO_CONTAINER::getReplicaSetUrl);
    }
}
