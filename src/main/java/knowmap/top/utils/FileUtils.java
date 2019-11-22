package knowmap.top.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static void save(InputStream in, String path) {
        try {
            Files.copy(in, Paths.get(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


    public static String getChecksum(InputStream in) throws IOException {
        return HashUtils.hash(io.undertow.util.FileUtils.readFile(in));
    }
}
