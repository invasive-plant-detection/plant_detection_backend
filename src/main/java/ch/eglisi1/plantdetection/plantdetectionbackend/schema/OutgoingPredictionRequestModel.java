package ch.eglisi1.plantdetection.plantdetectionbackend.schema;

/**
 * Model for outgoing prediction requests to the model API.
 * @param base64Image The image in base64 format.
 */
public record OutgoingPredictionRequestModel(String base64Image) {
}
