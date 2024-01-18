package ch.eglisi1.plantdetection.plantdetectionbackend.repository;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<PredictionDbo, Long> {
    PredictionDbo searchByPredictionId(Long predictionId);
}
