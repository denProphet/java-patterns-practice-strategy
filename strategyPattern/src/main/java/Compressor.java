import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import compressionStrategies.CompressionStrategy;

/**
 * Compress your file with the help of selected compress strategy algorithm.
 * Constructor-args: File fileToCompress, File outFile, CompressionStrategy strategy.
 */

public class Compressor {

    private final CompressionStrategy strategy;
    private final File fileToCompress;
    private final File outFile;

    public Compressor(final File fileToCompress, final File outFile, final CompressionStrategy strategy) {

        this.fileToCompress = fileToCompress;
        this.outFile = outFile;
        this.strategy = strategy;

    }

    public void compressFile() throws IOException {
        try (OutputStream os = new FileOutputStream(outFile)) {
            Files.copy(fileToCompress.toPath(), strategy.compress(os));
        }
    }

}
