package ch.eglisi1.plantdetection.plantdetectionbackend.schema;

import java.math.BigDecimal;

/**
 * Model for incoming prediction requests from the frontend.
 * @param base64Image The image in base64 format.
 * @param latitude The latitude of the location where the image was taken.
 * @param longitude The longitude of the location where the image was taken.
 */
public record IncomingPredictionRequestModel(String base64Image, BigDecimal latitude, BigDecimal longitude) {
}
