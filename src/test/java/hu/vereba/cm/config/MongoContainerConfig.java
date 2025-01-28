package hu.vereba.cm.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.MountableFile;

@TestConfiguration
public class MongoContainerConfig {

    private static final MongoDBContainer MONGO_CONTAINER;

    static {
        MONGO_CONTAINER = new MongoDBContainer("mongo:6.0.5")
                .withExposedPorts(27017)
                .waitingFor(Wait.forListeningPort());
        MONGO_CONTAINER.withCopyFileToContainer(
                MountableFile.forClasspathResource("mongo-init.js"), "/docker-entrypoint-initdb.d/mongo-init.js");
        MONGO_CONTAINER.start();
    }

    @Bean
    public MongoDBContainer mongoContainer() {
        return MONGO_CONTAINER;
    }

    @DynamicPropertySource
    static void registerMongoProperties(DynamicPropertyRegistry registry) {
        // Set Spring Data MongoDB properties based on the container's connection info
        registry.add("spring.data.mongodb.uri", MONGO_CONTAINER::getConnectionString);
    }
}
