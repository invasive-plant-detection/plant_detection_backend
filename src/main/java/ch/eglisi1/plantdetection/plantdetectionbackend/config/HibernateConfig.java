package ch.eglisi1.plantdetection.plantdetectionbackend.config;

import ch.eglisi1.plantdetection.plantdetectionbackend.namingstrategy.CustomPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
    /**
     * Custom hibernate physical naming strategy
     * @return the physical naming strategy
     */
    @Bean
    public CustomPhysicalNamingStrategy physicalNamingStrategy() {
        return new CustomPhysicalNamingStrategy();
    }
}
