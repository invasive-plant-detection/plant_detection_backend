package ch.eglisi1.plantdetection.plantdetectionbackend.util;

import ch.eglisi1.plantdetection.plantdetectionbackend.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class UtilTest {

    @Test
    void isBase64() throws IOException {
        // arrange
        String base64Image = TestUtil.readFileFromResources("", "base64Image.txt");

        // act
        boolean result = Util.isBase64(base64Image);

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    void isBase64_null() {
        // act
        boolean result = Util.isBase64(null);

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void isBase64_empty() {
        // act
        boolean result = Util.isBase64("");

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void isBase64_noBase64() {
        // act
        boolean result = Util.isBase64("Gibberish");

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    void isBase64_noBase64Characters() {
        // act
        boolean result = Util.isBase64("$$$");

        // assert
        Assertions.assertFalse(result);
    }
}