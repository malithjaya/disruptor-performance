import org.junit.Test;
import process.Process;
import workload.OutOfRangeException;

/**
 * Created by anoukh on 5/4/16.
 */
public class TestProcess {

    @Test
    public void testProcess(){
        try {
            new Process().process();
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
    }

}
