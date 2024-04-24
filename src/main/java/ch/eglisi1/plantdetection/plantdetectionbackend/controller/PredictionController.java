package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.schema.IncomingPredictionRequestModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.service.PredictionService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling prediction requests.
 */
@RestController
public class PredictionController {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PredictionController.class);
    private final PredictionService predictionService;

    /**
     * Constructor for the PredictionController.
     * @param predictionService The prediction service.
     */
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    /**
     * Endpoint for handling prediction requests.
     * @param request The incoming prediction request.
     * @return The prediction response.
     */
    @PostMapping("/predict")
    public PredictionResponseModel predict(@RequestBody IncomingPredictionRequestModel request) {
        logger.debug("Received request: {}", request);
        return predictionService.predict(request.base64Image(), request.latitude(), request.longitude());
    }
}
