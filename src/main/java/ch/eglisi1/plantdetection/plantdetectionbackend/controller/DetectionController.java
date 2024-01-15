package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.service.DetectionService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetectionController {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(DetectionController.class);
    private final DetectionService detectionService;

    public DetectionController(DetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @PostMapping("/predict")
    public PredictionResponseModel predict(@RequestBody String base64Image) {
        logger.debug("Received base64Image: " + base64Image);
        return detectionService.predict(base64Image);
    }
}
