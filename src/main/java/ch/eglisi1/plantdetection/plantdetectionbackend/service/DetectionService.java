package ch.eglisi1.plantdetection.plantdetectionbackend.service;

import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionRequestModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class DetectionService {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(DetectionService.class);
    private final String modelUrl;

    public DetectionService(@Value("${plant-detection.model.url}") String modelUrl) {
        this.modelUrl = modelUrl;
    }

    public PredictionResponseModel predict(String image) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = modelUrl + "/predict";

        try {
            ResponseEntity<PredictionResponseModel> response = restTemplate.postForEntity(uri, new PredictionRequestModel(image), PredictionResponseModel.class);
            logger.info("Response: " + response.getBody());
            return response.getBody();
        } catch (ResourceAccessException | RestClientResponseException e) {
            logger.error("Error during post call to " + uri, e);
            throw e;
        }
    }
}
