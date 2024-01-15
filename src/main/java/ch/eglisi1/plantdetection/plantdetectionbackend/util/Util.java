package ch.eglisi1.plantdetection.plantdetectionbackend.util;

import org.apache.commons.lang3.StringUtils;

public final class Util {
    private Util() {
    }

    /**
     * Checks if the given string is a base64 encoded string.
     * @param image the string to check
     * @return true if the string is base64 encoded, false otherwise
     */
    public static boolean isBase64(String image) {
        if (StringUtils.isBlank(image)) {
            return false;
        }
        if (!org.apache.commons.codec.binary.Base64.isBase64(image)) {
            return false;
        }

        try {
            java.util.Base64.getDecoder().decode(image);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
