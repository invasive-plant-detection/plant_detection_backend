package ch.eglisi1.plantdetection.plantdetectionbackend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "PREDICTION")
public class PredictionDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID predictionId;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 8)
    private BigDecimal longitude;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String image;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String prediction;

    public PredictionDbo() {
    }

    public PredictionDbo(UUID predictionId, BigDecimal latitude, BigDecimal longitude, LocalDateTime createdAt, String image) {
        this.predictionId = predictionId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.image = image;
    }

    public PredictionDbo(UUID predictionId, BigDecimal latitude, BigDecimal longitude, LocalDateTime createdAt, String image, String prediction) {
        this(predictionId, latitude, longitude, createdAt, image);
        this.prediction = prediction;
    }

    public UUID getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(UUID predictionId) {
        this.predictionId = predictionId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    @Override
    public String toString() {
        return "PredictionDbo{" +
                "predictionId=" + predictionId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                ", prediction='" + prediction + '\'' +
                '}';
    }
}
