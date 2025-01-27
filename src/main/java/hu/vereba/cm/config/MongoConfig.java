package hu.vereba.cm.config;

import hu.vereba.cm.converter.DateToOffsetDateTimeConverter;
import hu.vereba.cm.converter.OffsetDateTimeToDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new OffsetDateTimeToDateConverter(),
                new DateToOffsetDateTimeConverter()
        ));
    }
}