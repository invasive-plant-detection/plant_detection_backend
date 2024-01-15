package ch.eglisi1.plantdetection.plantdetectionbackend.schema;

public record PredictionResponseModel(String base64Image, String instruction) {
}
