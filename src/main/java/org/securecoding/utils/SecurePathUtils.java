package org.securecoding.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.util.regex.Pattern;

public final class SecurePathUtils {

    private static final List<String> SECURE_PATHS = Arrays.asList(
            "/usr/local/templates",
            "/nfs/local/Static-Reports",
            "/nfs/Static");

    public static boolean isPathManipulationPayload(String path) {
        // Regular Expression to detect "../", "..\" and "//" in the path.
        Pattern pattern = Pattern.compile("(/|\\\\)\\1|\\.{2}(/|\\\\)");
        return pattern.matcher(path).find();
    }

    public static boolean isSecurePath(String path) {

        if (isPathManipulationPayload(path))
            return false;

        Path inputPath = Paths.get(path.toLowerCase());

        for (String allowedPath : SECURE_PATHS) {
            Path allowed = Paths.get(allowedPath.toLowerCase());
            if (inputPath.startsWith(allowed)) {
                return true;
            }
        }

        return false;
    }
}
