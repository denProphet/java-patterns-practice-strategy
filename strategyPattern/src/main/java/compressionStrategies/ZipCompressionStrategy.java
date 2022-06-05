package compressionStrategies;


import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompressionStrategy implements CompressionStrategy {
    @Override
    public OutputStream compress(OutputStream data) {
        return new ZipOutputStream(data);
    }
}
