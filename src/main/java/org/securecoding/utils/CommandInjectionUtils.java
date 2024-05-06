package org.securecoding.utils;

public final class CommandInjectionUtils {

    public static String sanitizeInputCommandInjection(String input) {
        // Remove dangerous characters
        String sanitizedInput = input.replaceAll("[;&|'\"`<>{}\\[\\]\\\\]", "");
        return sanitizedInput;
    }    
}
