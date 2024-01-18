package ch.eglisi1.plantdetection.plantdetectionbackend.util;

import ch.eglisi1.plantdetection.plantdetectionbackend.namingstrategy.CustomPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
    @Bean
    public CustomPhysicalNamingStrategy physicalNamingStrategy() {
        return new CustomPhysicalNamingStrategy();
    }
}
