package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import ch.eglisi1.plantdetection.plantdetectionbackend.repository.PredictionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ObservationController {
    private final Logger logger = LoggerFactory.getLogger(ObservationController.class);

    private final PredictionRepository predictionRepository;

    @Autowired
    public ObservationController(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @GetMapping("/observations")
    public ResponseEntity<List<PredictionDbo>> getObservations() {
        try {
            List<PredictionDbo> result = this.predictionRepository.findAll();
            this.logger.debug("Found {} entities", result.size());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            this.logger.error("Error retrieving observations: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<PredictionDbo> findPredictionById(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Optional<PredictionDbo> prediction = predictionRepository.findById(id);
        return prediction
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
