package ch.eglisi1.plantdetection.plantdetectionbackend.schema;

/**
 * Model for the response to a prediction request to the frontend.
 * @param base64Image The image in base64 format.
 * @param instruction The instruction for the user.
 */
public record PredictionResponseModel(String base64Image, String instruction) {
}
