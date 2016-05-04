package workload;

/**
 * Created by anoukh on 5/4/16.
 * Exception thrown when workload size is out of range
 */
public class OutOfRangeException extends Exception {

    public OutOfRangeException() {
    }

    public OutOfRangeException(String message) {
        super(message);
    }

}
