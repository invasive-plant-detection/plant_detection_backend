package ch.eglisi1.plantdetection.plantdetectionbackend.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

public final class ImageGenerator {
    private ImageGenerator() {
    }

    /**
     * Generates a random image of the given width and height and returns it as a Base64 string.
     *
     * @param width  of picture
     * @param height of picture
     * @return Base64 encoded image
     * @throws IOException if the image cannot be written
     */
    public static String generateRandomBase64Image(int width, int height) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int red = rand.nextInt(256);
                int green = rand.nextInt(256);
                int blue = rand.nextInt(256);
                Color color = new Color(red, green, blue);
                image.setRGB(x, y, color.getRGB());
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] imageData = outputStream.toByteArray();

        return Base64.getEncoder().encodeToString(imageData);
    }
}