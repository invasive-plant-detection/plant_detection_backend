package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import ch.eglisi1.plantdetection.plantdetectionbackend.repository.PredictionRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ObservationController {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(ObservationController.class);

    private final PredictionRepository predictionRepository;

    @Autowired
    public ObservationController(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @GetMapping("/observations")
    public List<PredictionDbo> getObservations() {
        List<PredictionDbo> result = predictionRepository.findAll();
        logger.debug("Found {} entities", result.size());
        return result;
    }
}
