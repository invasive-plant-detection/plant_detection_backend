package ch.eglisi1.plantdetection.plantdetectionbackend.repository;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PredictionRepository extends JpaRepository<PredictionDbo, UUID> {
    PredictionDbo searchByPredictionId(UUID predictionId);
}
