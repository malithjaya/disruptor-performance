import process.Reader;
import workload.OutOfRangeException;

/**
 * Created by anoukh on 5/4/16.
 */
public class Main {
    public static void main(String[] args) throws OutOfRangeException {
        Reader readerThread = new Reader();
        readerThread.start();
    }
}
