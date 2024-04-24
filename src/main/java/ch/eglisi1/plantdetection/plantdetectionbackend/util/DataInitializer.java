package ch.eglisi1.plantdetection.plantdetectionbackend.util;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import ch.eglisi1.plantdetection.plantdetectionbackend.repository.PredictionRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for initializing data in the database in the dev environment.
 */
@Component
public class DataInitializer {
    private final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private final Random random = new Random();
    private final PredictionRepository predictionRepository;

    public final static int ENTITIES_TO_CREATE = 100;

    @Value("${environment.name}")
    private String environmentName;

    @Autowired
    public DataInitializer(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    /**
     * Method for initializing data in the database in the dev environment.
     */
    @PostConstruct
    public void initData() {
        if ("DEV".equals(environmentName)) {
            String base64Image = getBase64Image();
            String prediction = "Here will be my prediction that is better than a coin flip";
            for (int i = 0; i < ENTITIES_TO_CREATE; i++) {
                BigDecimal latitude = BigDecimal.valueOf(generateRandomCoordinate(46.8, 47.8)); // Approximate range for latitude in German-speaking Switzerland
                BigDecimal longitude = BigDecimal.valueOf(generateRandomCoordinate(6.9, 10.5)); // Approximate range for longitude in German-speaking Switzerland

                PredictionDbo predictionDbo = new PredictionDbo(null, latitude, longitude, randomDateTimeWithinLast100Days(), base64Image, prediction);
                PredictionDbo savedDbo = predictionRepository.save(predictionDbo);
                logger.debug("Created Entity:\n{}", savedDbo);
            }
            logger.info("Created {} entities", ENTITIES_TO_CREATE);
        }
    }

    /**
     * Method for generating a random base64 image.
     * @return The base64 image.
     */
    private String getBase64Image() {
        try {
            return ImageGenerator.generateRandomBase64Image(15, 15);
        } catch (IOException e) {
            return  "Placeholder";
        }
    }

    /**
     * Method for generating a random coordinate.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The random coordinate.
     */
    private double generateRandomCoordinate(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    /**
     * Method for generating a random date time within the last 100 days.
     * @return The random date time.
     */
    private LocalDateTime randomDateTimeWithinLast100Days() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime hundredDaysAgo = now.minusDays(100);

        long randomSeconds = ThreadLocalRandom.current().nextLong(ChronoUnit.SECONDS.between(hundredDaysAgo, now));
        return hundredDaysAgo.plusSeconds(randomSeconds);
    }
}
