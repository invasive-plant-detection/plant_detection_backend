package ch.eglisi1.plantdetection.plantdetectionbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    /**
     * Creates a new RestTemplate to be used for HTTP requests
     * It is implemented like this for easier testing of the PredictionService
     * @return the new RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
