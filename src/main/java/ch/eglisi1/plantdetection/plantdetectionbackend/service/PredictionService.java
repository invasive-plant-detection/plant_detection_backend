package ch.eglisi1.plantdetection.plantdetectionbackend.service;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import ch.eglisi1.plantdetection.plantdetectionbackend.repository.PredictionRepository;
import ch.eglisi1.plantdetection.plantdetectionbackend.schema.OutgoingPredictionRequestModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PredictionService {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PredictionService.class);
    private final String modelUrl;
    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionService(@Value("${plant-detection.model.url}") String modelUrl, PredictionRepository predictionRepository) {
        this.modelUrl = modelUrl;
        this.predictionRepository = predictionRepository;
    }

    public PredictionResponseModel predict(String image, BigDecimal latitude, BigDecimal longitude) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = modelUrl + "/predict";
        System.out.println(uri);
        try {
            ResponseEntity<PredictionResponseModel> response = restTemplate.postForEntity(uri, new OutgoingPredictionRequestModel(image), PredictionResponseModel.class);
            logger.info("Response: {}", response.getBody());
            String instruction;
            try {
                instruction = Objects.requireNonNull(response.getBody()).instruction();
            } catch (NullPointerException e) {
                instruction = "";
            }
            savePrediction(latitude, longitude, image, instruction);
            return response.getBody();
        } catch (ResourceAccessException | RestClientResponseException e) {
            logger.error("Error during post call to {}", uri, e);
            throw e;
        }
    }

    private void savePrediction(BigDecimal latitude, BigDecimal longitude, String image, String prediction) {
        PredictionDbo toBeSaved = new PredictionDbo(null, latitude, longitude, LocalDateTime.now(), image, prediction);
        predictionRepository.save(toBeSaved);
    }
}
