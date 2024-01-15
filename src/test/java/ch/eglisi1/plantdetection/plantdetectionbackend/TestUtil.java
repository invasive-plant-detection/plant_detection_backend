package ch.eglisi1.plantdetection.plantdetectionbackend;

import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class TestUtil {
    private static final String TEST_RESOURCES_PATH = "src/test/resources/";

    private TestUtil() {
    }

    /**
     * Reads a file from the test resources directory and returns its content as a String.
     * @param filename The name of the file to read.
     * @param path The path within the resources directory, if any.
     * @return The content of the file as a String.
     * @throws IOException if there is an error reading the file.
     */
    public static String readFileFromResources(String path, String filename) throws IOException {
        // Construct the complete path
        String fullPath = TEST_RESOURCES_PATH + (!StringUtils.isBlank(path) ? path + "/" : "") + filename;

        // Read the file content
        return new String(Files.readAllBytes(Paths.get(fullPath)));
    }
}
