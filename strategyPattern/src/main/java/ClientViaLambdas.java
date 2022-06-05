import java.io.File;
import java.io.IOException;
import java.util.UnknownFormatConversionException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

public class ClientViaLambdas {

    public static void main(String[] args) {

        if (args.length != 3) {
            throw new UnknownFormatConversionException("you have to pass " +
                    "args[0] = fileToCompressPath & " +
                    "args[1] = outFilePath & " +
                    "args[2] = strategyName");
        }

        //get Command-line args
        String fileToCompressPath = args[0];
        String outFilePath = args[1];
        String strategyName = args[2];

        //get inFile & outFile via pointed paths
        File fileToCompress = new File(fileToCompressPath);
        File outFile = new File(outFilePath);

        //Class with method to
        //compress your file with the help of selected compress strategy algorithm.
        Compressor compressor;

        //Create compressor instance by selected strategy
        if (strategyName.equalsIgnoreCase("zip")) {
            compressor = new Compressor(fileToCompress, outFile, (ZipOutputStream::new));
        } else {
            compressor = new Compressor(fileToCompress, outFile,
                    data -> {
                        try {
                            return new GZIPOutputStream(data);
                        } catch (IOException e) {
                            throw new RuntimeException();
                        }

                    });
        }

        //Compress file by special compressor method invoking

        try {
            compressor.compressFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   /* private final UnaryOperator<OutputStream> zipCompress = ZipOutputStream::new;

    private final UnaryOperator<OutputStream> gzipCompress = data -> {
        try {
            return new GZIPOutputStream(data);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    };*/


}
