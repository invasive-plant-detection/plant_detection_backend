package ch.eglisi1.plantdetection.plantdetectionbackend.schema;

import java.math.BigDecimal;

public record IncomingPredictionRequestModel(String base64Image, BigDecimal latitude, BigDecimal longitude) {
}
