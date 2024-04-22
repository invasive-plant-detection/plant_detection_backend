package ch.eglisi1.plantdetection.plantdetectionbackend.util;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import ch.eglisi1.plantdetection.plantdetectionbackend.repository.PredictionRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataInitializer {
    private final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private final Random random = new Random();
    private final PredictionRepository predictionRepository;

    @Autowired
    public DataInitializer(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @PostConstruct
    public void initData() {
        int entitiesToCreate = 100;
        String base64Image = "placeholder_for_base64_image";
        String prediction = "Here will be my prediction that is better than a coin flip";
        for (int i = 0; i < entitiesToCreate; i++) {
            BigDecimal latitude = BigDecimal.valueOf(generateRandomCoordinate(46.8, 47.8)); // Approximate range for latitude in German-speaking Switzerland
            BigDecimal longitude = BigDecimal.valueOf(generateRandomCoordinate(6.9, 10.5)); // Approximate range for longitude in German-speaking Switzerland

            PredictionDbo predictionDbo = new PredictionDbo(null, latitude, longitude, randomDateTimeWithinLast100Days(), base64Image, prediction);
            PredictionDbo savedDbo = predictionRepository.save(predictionDbo);
            logger.debug("Created Entity:\n" + savedDbo);
        }
        logger.info("Created {} entities", entitiesToCreate);
    }

    private double generateRandomCoordinate(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    private LocalDateTime randomDateTimeWithinLast100Days() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime hundredDaysAgo = now.minusDays(100);

        long randomSeconds = ThreadLocalRandom.current().nextLong(ChronoUnit.SECONDS.between(hundredDaysAgo, now));
        return hundredDaysAgo.plusSeconds(randomSeconds);
    }
}
