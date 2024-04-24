package ch.eglisi1.plantdetection.plantdetectionbackend.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.regex.Pattern;

class ImageGeneratorTest {

    @Test
    void generateRandomBase64Image_ReturnsValidBase64() throws Exception {
        // Arrange
        int width = 100;
        int height = 100;

        // Act
        String base64Image = ImageGenerator.generateRandomBase64Image(width, height);

        // Assert
        Assertions.assertNotNull(base64Image, "The generated Base64 string should not be null");
        Assertions.assertFalse(base64Image.isEmpty(), "The generated Base64 string should not be empty");

        // Basic regex to check if string is Base64
        Assertions.assertTrue(Pattern.matches("^[A-Za-z0-9+/]+={0,2}$", base64Image), "The string should be valid Base64");
    }

    @Test
    void generateRandomBase64Image_HasExpectedDimensions() throws Exception {
        // Arrange
        int width = 50;
        int height = 50;

        // Act
        String base64Image = ImageGenerator.generateRandomBase64Image(width, height);

        // Convert Base64 back to byte array and check dimensions via BufferedImage
        byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
        BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(imageBytes));

        // Assert
        Assertions.assertNotNull(image, "The decoded image should not be null");
        Assertions.assertEquals(width, image.getWidth(), "Image width should match the expected width");
        Assertions.assertEquals(height, image.getHeight(), "Image height should match the expected height");
    }
}